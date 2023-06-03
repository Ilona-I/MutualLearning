package ua.nure.illiashenko.mutuallearning.dto.test.question;

import lombok.Builder;
import lombok.Data;
import ua.nure.illiashenko.mutuallearning.constants.AnswerType;
import ua.nure.illiashenko.mutuallearning.dto.test.question.answer.AnswerToUpdateResponse;

@Data
@Builder
public class QuestionToUpdateResponse {

    private Integer questionId;
    private String text;
    private AnswerType type;
    private AnswerToUpdateResponse[] answerToUpdateResponses;
}
