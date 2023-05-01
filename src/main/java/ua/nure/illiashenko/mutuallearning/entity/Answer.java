package ua.nure.illiashenko.mutuallearning.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Answer {

    private int id;
    private int questionId;
    private String text;
    private int point;
}
