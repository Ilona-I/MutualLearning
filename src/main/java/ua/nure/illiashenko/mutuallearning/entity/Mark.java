package ua.nure.illiashenko.mutuallearning.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mark {

    private int id;
    private String title;
    private String description;
}
