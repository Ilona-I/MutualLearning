package ua.nure.illiashenko.mutuallearning.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Question {

    private int id;
    private int testId;
    private String text;
    private String type;
}
