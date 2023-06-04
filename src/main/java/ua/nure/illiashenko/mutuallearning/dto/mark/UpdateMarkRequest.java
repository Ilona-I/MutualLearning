package ua.nure.illiashenko.mutuallearning.dto.mark;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMarkRequest {

    private int id;
    @NotEmpty(message = "emptyUpdateMarkRequestTitle")
    private String title;
    @NotEmpty(message = "emptyUpdateMarkRequestDescription")
    private String description;
}
