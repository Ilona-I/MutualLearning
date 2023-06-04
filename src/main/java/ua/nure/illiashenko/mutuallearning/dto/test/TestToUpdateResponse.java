package ua.nure.illiashenko.mutuallearning.dto.test;

import lombok.Builder;
import lombok.Data;
import ua.nure.illiashenko.mutuallearning.dto.test.question.QuestionToUpdateResponse;

@Data
@Builder
public class TestToUpdateResponse {

    private String title;
    private QuestionToUpdateResponse[] questions;
}
