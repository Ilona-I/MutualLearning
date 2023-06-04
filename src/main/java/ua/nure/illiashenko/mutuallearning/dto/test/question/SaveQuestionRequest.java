package ua.nure.illiashenko.mutuallearning.dto.test.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.nure.illiashenko.mutuallearning.constants.AnswerType;
import ua.nure.illiashenko.mutuallearning.dto.test.question.answer.SaveAnswerRequest;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveQuestionRequest {

    private Integer questionId;
    private String text;
    private String type;
    private SaveAnswerRequest[] saveAnswerRequests;

}
