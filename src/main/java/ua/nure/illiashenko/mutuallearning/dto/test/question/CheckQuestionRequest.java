package ua.nure.illiashenko.mutuallearning.dto.test.question;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckQuestionRequest {

    @NotEmpty(message = "emptyCheckQuestionRequestQuestionId")
    private Integer questionId;
    private String[] answers;
}
