package ua.nure.illiashenko.mutuallearning.dto.test.question;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckQuestionResponse {

    private Integer questionId;
    private Integer mark;
    private Integer maxMark;
}
