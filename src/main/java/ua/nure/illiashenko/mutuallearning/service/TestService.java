package ua.nure.illiashenko.mutuallearning.service;

import static ua.nure.illiashenko.mutuallearning.constants.AnswerType.ONE_ANSWER;
import static ua.nure.illiashenko.mutuallearning.constants.AnswerType.SEVERAL_ANSWERS;

import java.sql.Timestamp;
import lombok.extern.slf4j.Slf4j;
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
import ua.nure.illiashenko.mutuallearning.dto.test.question.answer.AnswerResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.question.answer.AnswerToUpdateResponse;

@Slf4j
@Service
public class TestService {

    public void createTest(SaveTestRequest saveTestRequest) {
        System.out.println(saveTestRequest);
    }

    public void editTest(int id, SaveTestRequest saveTestRequest) {
        System.out.println(saveTestRequest);
    }

    public TestToUpdateResponse getTestToUpdate(int id) {
        return TestToUpdateResponse.builder()
            .title("title1")
            .questions(
                new QuestionToUpdateResponse[]{
                    QuestionToUpdateResponse.builder()
                        .questionId(1)
                        .text("Question text 1")
                        .type(ONE_ANSWER)
                        .answers(
                            new AnswerToUpdateResponse[]{
                                AnswerToUpdateResponse.builder()
                                    .answerId(1)
                                    .text("Answer 1 text")
                                    .mark(0)
                                    .build(),
                                AnswerToUpdateResponse.builder()
                                    .answerId(2)
                                    .text("Answer 2 text")
                                    .mark(1)
                                    .build()
                            }
                        )
                        .build(),
                    QuestionToUpdateResponse.builder()
                        .questionId(2)
                        .text("Question text 1")
                        .type(SEVERAL_ANSWERS)
                        .answers(
                            new AnswerToUpdateResponse[]{
                                AnswerToUpdateResponse.builder()
                                    .answerId(3)
                                    .text("Answer 1 text")
                                    .mark(0)
                                    .build(),
                                AnswerToUpdateResponse.builder()
                                    .answerId(4)
                                    .text("Answer 2 text")
                                    .mark(1)
                                    .build(),
                                AnswerToUpdateResponse.builder()
                                    .answerId(5)
                                    .text("Answer 3 text")
                                    .mark(1)
                                    .build()
                            }
                        )
                        .build()
                }
            )
            .build();
    }

    public TestInfoResponse getTestInfo(int id) {
        return TestInfoResponse.builder()
            .title("title1")
            .maxMark(10)
            .role("CREATOR")
            .userCount(2)
            .userAverageMark(6.5)
            .ownPreviousAttempts(new PreviousAttemptsResponse[]{
                PreviousAttemptsResponse.builder()
                    .startDateTime(new Timestamp(System.currentTimeMillis()))
                    .finishDateTime(new Timestamp(System.currentTimeMillis()))
                    .mark(7)
                    .build(),
                PreviousAttemptsResponse.builder()
                    .startDateTime(new Timestamp(System.currentTimeMillis()))
                    .finishDateTime(new Timestamp(System.currentTimeMillis()))
                    .mark(6)
                    .build()
            })
            .usersAttemptsResponse(
                new UsersAttemptsResponse[]{
                    UsersAttemptsResponse.builder()
                        .userLogin("user2")
                        .previousAttempts(
                            new PreviousAttemptsResponse[]{
                                PreviousAttemptsResponse.builder()
                                    .startDateTime(new Timestamp(System.currentTimeMillis()))
                                    .finishDateTime(new Timestamp(System.currentTimeMillis()))
                                    .mark(7)
                                    .build(),
                                PreviousAttemptsResponse.builder()
                                    .startDateTime(new Timestamp(System.currentTimeMillis()))
                                    .finishDateTime(new Timestamp(System.currentTimeMillis()))
                                    .mark(6)
                                    .build()
                            }
                        )
                        .build(),
                    UsersAttemptsResponse.builder()
                        .userLogin("user3")
                        .previousAttempts(
                            new PreviousAttemptsResponse[]{
                                PreviousAttemptsResponse.builder()
                                    .startDateTime(new Timestamp(System.currentTimeMillis()))
                                    .finishDateTime(new Timestamp(System.currentTimeMillis()))
                                    .mark(7)
                                    .build(),
                                PreviousAttemptsResponse.builder()
                                    .startDateTime(new Timestamp(System.currentTimeMillis()))
                                    .finishDateTime(new Timestamp(System.currentTimeMillis()))
                                    .mark(6)
                                    .build()
                            }
                        )
                        .build()
                }
            )
            .build();
    }

    public TestResponse getTest(int id) {
        return TestResponse.builder()
            .title("title1")
            .questionResponses(new QuestionResponse[]{
                QuestionResponse.builder()
                    .questionId(1)
                    .text("Question text 1")
                    .type(ONE_ANSWER)
                    .answerResponses(
                        new AnswerResponse[]{
                            AnswerResponse.builder()
                                .answerId(1)
                                .text("Answer 1 text")
                                .build(),
                            AnswerResponse.builder()
                                .answerId(2)
                                .text("Answer 2 text")
                                .build()
                        }
                    )
                    .build(),
                QuestionResponse.builder()
                    .questionId(2)
                    .text("Question text 1")
                    .type(SEVERAL_ANSWERS)
                    .answerResponses(
                        new AnswerResponse[]{
                            AnswerResponse.builder()
                                .answerId(3)
                                .text("Answer 1 text")
                                .build(),
                            AnswerResponse.builder()
                                .answerId(4)
                                .text("Answer 2 text")
                                .build(),
                            AnswerResponse.builder()
                                .answerId(5)
                                .text("Answer 3 text")
                                .build()
                        }
                    )
                    .build()

            })
            .build();
    }

    public void sendTestToCheck(int id, CheckTestRequest checkTestRequest) {
        System.out.println(checkTestRequest);
    }

    public void deleteTest(int id) {

    }
}
