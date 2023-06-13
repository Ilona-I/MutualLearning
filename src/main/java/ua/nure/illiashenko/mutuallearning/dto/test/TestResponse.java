package ua.nure.illiashenko.mutuallearning.dto.test;

import lombok.Builder;
import lombok.Data;
import ua.nure.illiashenko.mutuallearning.dto.test.question.QuestionResponse;

@Data
@Builder
public class TestResponse {

    private String title;
    private QuestionResponse[] questions;
}
