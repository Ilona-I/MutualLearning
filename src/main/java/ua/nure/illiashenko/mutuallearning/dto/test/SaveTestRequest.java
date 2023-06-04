package ua.nure.illiashenko.mutuallearning.dto.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.nure.illiashenko.mutuallearning.dto.test.question.SaveQuestionRequest;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveTestRequest {

    private String title;
    private SaveQuestionRequest[] saveQuestionRequests;
}
