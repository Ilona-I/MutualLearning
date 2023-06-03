package ua.nure.illiashenko.mutuallearning.dto.test;

import lombok.Builder;
import lombok.Data;
import ua.nure.illiashenko.mutuallearning.constants.TestType;
import ua.nure.illiashenko.mutuallearning.dto.test.question.CheckQuestionResponse;

@Data
@Builder
public class CheckTestResponse {

    private TestType type;
    private CheckQuestionResponse[] checkQuestionResponses;
}
