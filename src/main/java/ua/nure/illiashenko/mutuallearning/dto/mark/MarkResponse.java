package ua.nure.illiashenko.mutuallearning.dto.mark;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MarkResponse {

    private int id;
    private String title;
    private String type;
    private String description;
    private String creator;
}
