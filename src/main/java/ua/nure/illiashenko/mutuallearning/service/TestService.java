package ua.nure.illiashenko.mutuallearning.service;

import static ua.nure.illiashenko.mutuallearning.constants.ArticleUserRole.ARTICLE_CREATOR;
import static ua.nure.illiashenko.mutuallearning.constants.SystemUserRole.PREMIUM_USER;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.nure.illiashenko.mutuallearning.dto.test.CheckTestRequest;
import ua.nure.illiashenko.mutuallearning.dto.test.PreviousAttemptsResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.SaveTestRequest;
import ua.nure.illiashenko.mutuallearning.dto.test.TestInfoResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.TestResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.TestToUpdateResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.UsersAttemptsResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.question.QuestionResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.question.QuestionToUpdateResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.question.SaveQuestionRequest;
import ua.nure.illiashenko.mutuallearning.dto.test.question.answer.AnswerResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.question.answer.AnswerToUpdateResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.question.answer.SaveAnswerRequest;
import ua.nure.illiashenko.mutuallearning.entity.Answer;
import ua.nure.illiashenko.mutuallearning.entity.Question;
import ua.nure.illiashenko.mutuallearning.entity.Test;
import ua.nure.illiashenko.mutuallearning.entity.User;
import ua.nure.illiashenko.mutuallearning.entity.UserArticle;
import ua.nure.illiashenko.mutuallearning.entity.UserTest;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;
import ua.nure.illiashenko.mutuallearning.repository.AnswerRepository;
import ua.nure.illiashenko.mutuallearning.repository.QuestionRepository;
import ua.nure.illiashenko.mutuallearning.repository.TestRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserArticleRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserTestRepository;

@Slf4j
@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserTestRepository userTestRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserArticleRepository userArticleRepository;
    @Autowired
    private UserRepository userRepository;

    public void createTest(String login, int articleId, SaveTestRequest saveTestRequest) {
        checkAccessByArticleId(login, articleId);
        final Test test = new Test();
        test.setArticleId(articleId);
        test.setTitle(saveTestRequest.getTitle());
        final Test savedTest = testRepository.save(test);
        for (SaveQuestionRequest saveQuestionRequest : saveTestRequest.getQuestions()) {
            final Question question = new Question();
            question.setTestId(savedTest.getId());
            question.setText(saveQuestionRequest.getText());
            question.setType(saveQuestionRequest.getType());
            final Question savedQuestion = questionRepository.save(question);
            for (SaveAnswerRequest saveAnswerRequest : saveQuestionRequest.getAnswers()) {
                final Answer answer = new Answer();
                answer.setQuestionId(savedQuestion.getId());
                answer.setText(saveAnswerRequest.getText());
                answer.setPoint(saveAnswerRequest.getMark());
                answerRepository.save(answer);
            }
        }
    }

    public void editTest(String login, int id, SaveTestRequest saveTestRequest) {
        checkAccessByTestId(login, id);
        final Test test = testRepository.findById(id)
            .orElseThrow(() -> new ServiceApiException(List.of("testNotFound"), HttpStatus.NOT_FOUND));
        test.setTitle(saveTestRequest.getTitle());
        testRepository.save(test);
        deleteOldQuestionsWithAnswers(id, saveTestRequest);
        for (SaveQuestionRequest saveQuestionRequest : saveTestRequest.getQuestions()) {
            final Question question = new Question();
            final Optional<Question> dbQuestion = questionRepository.findById(saveQuestionRequest.getQuestionId());
            if (dbQuestion.isPresent() && dbQuestion.get().getTestId() == id) {
                question.setId(saveQuestionRequest.getQuestionId());
                final List<Integer> oldAnswersId = answerRepository.findByQuestionId(
                        saveQuestionRequest.getQuestionId())
                    .stream()
                    .map(Answer::getId)
                    .collect(Collectors.toList());
                oldAnswersId.removeAll(Arrays.stream(saveQuestionRequest.getAnswers()).map(
                    SaveAnswerRequest::getAnswerId).collect(
                    Collectors.toList()));
                answerRepository.deleteAllById(oldAnswersId);
            }
            question.setTestId(test.getId());
            question.setText(saveQuestionRequest.getText());
            question.setType(saveQuestionRequest.getType());
            final Question savedQuestion = questionRepository.save(question);
            for (SaveAnswerRequest saveAnswerRequest : saveQuestionRequest.getAnswers()) {
                final Answer answer = new Answer();
                final Optional<Answer> dbAnswer = answerRepository.findById(saveAnswerRequest.getAnswerId());
                if (dbAnswer.isPresent() && Objects.equals(dbAnswer.get().getQuestionId(), savedQuestion.getId())) {
                    answer.setId(saveAnswerRequest.getAnswerId());
                }
                answer.setQuestionId(savedQuestion.getId());
                answer.setText(saveAnswerRequest.getText());
                answer.setPoint(saveAnswerRequest.getMark());
                answerRepository.save(answer);
            }
        }
    }

    private void deleteOldQuestionsWithAnswers(int id, SaveTestRequest saveTestRequest) {
        final List<Integer> oldQuestionsId = questionRepository.findByTestId(id)
            .stream()
            .map(Question::getId)
            .collect(Collectors.toList());
        oldQuestionsId.removeAll(Arrays.stream(saveTestRequest.getQuestions())
            .map(SaveQuestionRequest::getQuestionId)
            .collect(Collectors.toList()));
        for (Integer oldQuestionId : oldQuestionsId) {
            answerRepository.deleteAll(answerRepository.findByQuestionId(oldQuestionId));
            questionRepository.deleteById(oldQuestionId);
        }
    }

    public TestToUpdateResponse getTestToUpdate(String login, int id) {
        checkAccessByTestId(login, id);
        final Test test = testRepository.findById(id)
            .orElseThrow(() -> new ServiceApiException(HttpStatus.NOT_FOUND));
        final QuestionToUpdateResponse[] questionResponses = questionRepository.findByTestId(id)
            .stream()
            .map(question -> {
                final AnswerToUpdateResponse[] answerResponses = answerRepository.findByQuestionId(question.getId())
                    .stream()
                    .map(answer -> AnswerToUpdateResponse.builder()
                        .answerId(answer.getId())
                        .mark(answer.getPoint())
                        .text(answer.getText())
                        .build())
                    .toArray(AnswerToUpdateResponse[]::new);
                return QuestionToUpdateResponse.builder()
                    .questionId(question.getId())
                    .text(question.getText())
                    .type(question.getType())
                    .answers(answerResponses)
                    .build();
            })
            .toArray(QuestionToUpdateResponse[]::new);
        return TestToUpdateResponse.builder()
            .title(test.getTitle())
            .questions(questionResponses)
            .build();
    }

    public TestInfoResponse getTestInfo(String login, int id) {
        final Test test = testRepository.findById(id)
            .orElseThrow(() -> new ServiceApiException(HttpStatus.NOT_FOUND));
        final Integer maxMark = getMaxMark(id);
        final PreviousAttemptsResponse[] ownPreviousAttempts = getOwnPreviousAttempts(login, id);
        final Optional<UserArticle> optionalUserArticle = userArticleRepository.findByUserLoginAndArticleId(login,
            test.getArticleId());
        String role = "";
        if (optionalUserArticle.isPresent()) {
            role = optionalUserArticle.get().getRole();
        }
        int userCount = 0;
        int sumMarks = 0;
        final List<UserTest> userTests = userTestRepository.findByTestIdOrderByUserLoginAscDateTimeDesc(id);
        final List<UsersAttemptsResponse> usersAttemptsResponses = new ArrayList<>();

        UserTest currentUserTest = null;
        List<PreviousAttemptsResponse> previousAttempts = new ArrayList<>();
        for (UserTest userTest : userTests) {
            if (currentUserTest == null) {
                currentUserTest = userTest;
                userCount++;
            } else if (currentUserTest != userTest) {
                if (isPremiumCreator(role, login)) {
                    usersAttemptsResponses.add(UsersAttemptsResponse.builder()
                        .userLogin(currentUserTest.getUserLogin())
                        .previousAttempts(previousAttempts.toArray(PreviousAttemptsResponse[]::new))
                        .build());
                    previousAttempts.clear();
                }
                currentUserTest = userTest;
                userCount++;
            }
            sumMarks += userTest.getMark();
            if (isPremiumCreator(role, login)) {
                previousAttempts.add(PreviousAttemptsResponse.builder()
                    .dateTime(userTest.getDateTime())
                    .mark(userTest.getMark())
                    .build());
            }

        }
        if (currentUserTest != null && isPremiumCreator(role, login)) {
            usersAttemptsResponses.add(UsersAttemptsResponse.builder()
                .userLogin(currentUserTest.getUserLogin())
                .previousAttempts(previousAttempts.toArray(PreviousAttemptsResponse[]::new))
                .build());
        }
        final Double userAverageMark = ((double) sumMarks) / userTests.size();

        final TestInfoResponse testInfoResponse = new TestInfoResponse();
        testInfoResponse.setTitle(test.getTitle());
        testInfoResponse.setMaxMark(maxMark);
        testInfoResponse.setOwnPreviousAttempts(ownPreviousAttempts);
        testInfoResponse.setRole(role);
        testInfoResponse.setUserCount(userCount);
        testInfoResponse.setUserAverageMark(userAverageMark);
        if (isPremiumCreator(role, login)) {
            testInfoResponse.setUsersAttemptsResponse(usersAttemptsResponses.toArray(UsersAttemptsResponse[]::new));
        }
        return testInfoResponse;
    }

    private boolean isPremiumCreator(String role, String login) {
        return ARTICLE_CREATOR.equals(role) && isPremiumUser(login);
    }

    private PreviousAttemptsResponse[] getOwnPreviousAttempts(String login, int testId) {
        return userTestRepository.findByTestIdAndUserLogin(testId, login)
            .stream()
            .map(userTest -> PreviousAttemptsResponse.builder()
                .mark(userTest.getMark())
                .dateTime(userTest.getDateTime())
                .build())
            .toArray(PreviousAttemptsResponse[]::new);
    }

    private int getMaxMark(int id) {
        int maxMark = 0;
        final List<Integer> questionsId = questionRepository.findByTestId(id).stream()
            .map(Question::getId)
            .collect(Collectors.toList());
        for (Integer questionId : questionsId) {
            List<Answer> answers = answerRepository.findByQuestionId(questionId);
            for (Answer answer : answers) {
                maxMark += answer.getPoint();
            }
        }
        return maxMark;
    }

    public TestResponse getTest(int id) {
        final Test test = testRepository.findById(id)
            .orElseThrow(() -> new ServiceApiException(HttpStatus.NOT_FOUND));
        final QuestionResponse[] questionResponses = questionRepository.findByTestId(id)
            .stream()
            .map(question -> {
                final AnswerResponse[] answerResponses = answerRepository.findByQuestionId(question.getId())
                    .stream()
                    .map(answer -> AnswerResponse.builder()
                        .answerId(answer.getId())
                        .text(answer.getText())
                        .build())
                    .toArray(AnswerResponse[]::new);
                return QuestionResponse.builder()
                    .questionId(question.getId())
                    .text(question.getText())
                    .type(question.getType())
                    .answers(answerResponses)
                    .build();
            })
            .toArray(QuestionResponse[]::new);
        return TestResponse.builder()
            .title(test.getTitle())
            .questions(questionResponses)
            .build();
    }

    public void sendTestToCheck(String login, int id, CheckTestRequest checkTestRequest) {
        int mark = 0;
        for (Integer answerId : checkTestRequest.getAnswersId()) {
            final Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
            if (optionalAnswer.isPresent()) {
                mark += optionalAnswer.get().getPoint();
            }
        }
        final UserTest userTest = new UserTest();
        userTest.setUserLogin(login);
        userTest.setTestId(id);
        userTest.setDateTime(new Timestamp(System.currentTimeMillis()));
        userTest.setMark(mark);
        userTestRepository.save(userTest);
    }

    public void deleteTest(String login, int id) {
        checkAccessByTestId(login, id);
        testRepository.deleteById(id);
    }

    private boolean isPremiumUser(String login) {
        final Optional<User> optionalUser = userRepository.findById(login);
        return optionalUser.map(user -> user.getRole()
            .equals(PREMIUM_USER)).orElse(false);
    }

    private void checkAccessByArticleId(String login, Integer articleId) {
        final UserArticle userArticle = userArticleRepository.findByUserLoginAndArticleId(login, articleId)
            .orElseThrow(() -> new ServiceApiException(HttpStatus.FORBIDDEN));
        if (!userArticle.getRole().equals(ARTICLE_CREATOR)) {
            throw new ServiceApiException(HttpStatus.FORBIDDEN);
        }
    }

    private void checkAccessByTestId(String login, Integer testId) {
        final Test test = testRepository.findById(testId)
            .orElseThrow(() -> new ServiceApiException(HttpStatus.NOT_FOUND));
        checkAccessByArticleId(login, test.getArticleId());
    }
}
