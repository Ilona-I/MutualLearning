package ua.nure.illiashenko.mutuallearning.dto.mark;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.nure.illiashenko.mutuallearning.annotation.mark.UniqueMarkValidation;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMarkRequest {

    @NotEmpty(message = "emptyCreateMarkRequestTitle")
    @UniqueMarkValidation
    private String title;
    @NotEmpty(message = "emptyCreateMarkRequestDescription")
    private String description;

}
