package ua.nure.illiashenko.mutuallearning.dto.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.nure.illiashenko.mutuallearning.dto.test.question.CheckQuestionRequest;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckTestRequest {

    private Integer[] answersId;
}
