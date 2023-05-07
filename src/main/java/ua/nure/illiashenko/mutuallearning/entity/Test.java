package ua.nure.illiashenko.mutuallearning.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Test {

    private int id;
    private int articleId;
    private String name;
    private String description;
    private int numberOfAttempt;
    private String type;
}
