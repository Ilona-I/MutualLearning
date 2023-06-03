package ua.nure.illiashenko.mutuallearning.dto.test.question.answer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerToUpdateResponse {

    private Integer answerId;
    private String text;
    private Integer mark;
}
