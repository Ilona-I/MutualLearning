package ua.nure.illiashenko.mutuallearning.dto.mark;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMarkRequest {

    private String title;
    private String description;

}
