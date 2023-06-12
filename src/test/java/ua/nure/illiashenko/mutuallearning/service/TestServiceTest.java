package ua.nure.illiashenko.mutuallearning.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.nure.illiashenko.mutuallearning.BaseSpringBootTest;
import ua.nure.illiashenko.mutuallearning.constants.AnswerType;
import ua.nure.illiashenko.mutuallearning.constants.ArticleUserRole;
import ua.nure.illiashenko.mutuallearning.dto.test.SaveTestRequest;
import ua.nure.illiashenko.mutuallearning.dto.test.question.SaveQuestionRequest;
import ua.nure.illiashenko.mutuallearning.dto.test.question.answer.SaveAnswerRequest;
import ua.nure.illiashenko.mutuallearning.entity.Question;
import ua.nure.illiashenko.mutuallearning.entity.UserArticle;
import ua.nure.illiashenko.mutuallearning.repository.AnswerRepository;
import ua.nure.illiashenko.mutuallearning.repository.QuestionRepository;
import ua.nure.illiashenko.mutuallearning.repository.TestRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserArticleRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserTestRepository;

public class TestServiceTest extends BaseSpringBootTest {

    @MockBean
    private TestRepository testRepository;
    @MockBean
    private AnswerRepository answerRepository;
    @MockBean
    private UserTestRepository userTestRepository;
    @MockBean
    private QuestionRepository questionRepository;
    @MockBean
    private UserArticleRepository userArticleRepository;
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private TestService testService;

    @Test
    void shouldCreateTest(){
        final UserArticle userArticle = new UserArticle();
        userArticle.setUserLogin("login");
        userArticle.setArticleId(1);
        userArticle.setId(1);
        userArticle.setRole(ArticleUserRole.ARTICLE_CREATOR);
        final ua.nure.illiashenko.mutuallearning.entity.Test savedTest = new ua.nure.illiashenko.mutuallearning.entity.Test();
        savedTest.setId(1);
        savedTest.setTitle("title");
        savedTest.setArticleId(1);
        final Question savedQuestion = new Question();
        savedQuestion.setId(1);
        savedQuestion.setText("text");
        savedQuestion.setTestId(1);
        savedQuestion.setType(AnswerType.ONE_ANSWER);
        final SaveTestRequest saveTestRequest = SaveTestRequest.builder()
            .title("title")
            .articleId("1")
            .questions(new SaveQuestionRequest[]{
                SaveQuestionRequest.builder()
                    .questionId(1)
                    .text("text")
                    .type(AnswerType.ONE_ANSWER)
                    .answers(new SaveAnswerRequest[]{
                        SaveAnswerRequest.builder()
                            .answerId(1)
                            .text("text")
                            .mark(1)
                            .build()
                    })
                    .build()
            })
            .build();
        when(userArticleRepository.findByUserLoginAndArticleId(anyString(),anyInt()))
            .thenReturn(Optional.of(userArticle));
        when(testRepository.save(notNull()))
            .thenReturn(savedTest);
        when(questionRepository.save(notNull()))
            .thenReturn(savedQuestion);

        testService.createTest("login", 1, saveTestRequest);

        verify(answerRepository, times(1)).save(any());
    }

    @Test
    void shouldEditTest(){
        final UserArticle userArticle = new UserArticle();
        userArticle.setUserLogin("login");
        userArticle.setArticleId(1);
        userArticle.setId(1);
        userArticle.setRole(ArticleUserRole.ARTICLE_CREATOR);
        final ua.nure.illiashenko.mutuallearning.entity.Test savedTest = new ua.nure.illiashenko.mutuallearning.entity.Test();
        savedTest.setId(1);
        savedTest.setTitle("title");
        savedTest.setArticleId(1);
        final Question savedQuestion = new Question();
        savedQuestion.setId(1);
        savedQuestion.setText("text");
        savedQuestion.setTestId(1);
        savedQuestion.setType(AnswerType.ONE_ANSWER);
        final SaveTestRequest saveTestRequest = SaveTestRequest.builder()
            .title("title")
            .articleId("1")
            .questions(new SaveQuestionRequest[]{
                SaveQuestionRequest.builder()
                    .questionId(1)
                    .text("text")
                    .type(AnswerType.ONE_ANSWER)
                    .answers(new SaveAnswerRequest[]{
                        SaveAnswerRequest.builder()
                            .answerId(1)
                            .text("text")
                            .mark(1)
                            .build()
                    })
                    .build()
            })
            .build();
        when(testRepository.findById(1))
            .thenReturn(Optional.of(savedTest));
        when(userArticleRepository.findByUserLoginAndArticleId(anyString(),anyInt()))
            .thenReturn(Optional.of(userArticle));
        when(testRepository.save(notNull()))
            .thenReturn(savedTest);
        when(questionRepository.save(notNull()))
            .thenReturn(savedQuestion);

        testService.editTest("login", 1, saveTestRequest);

        verify(answerRepository, times(1)).save(any());
    }

}
