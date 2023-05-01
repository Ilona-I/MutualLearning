package ua.nure.illiashenko.mutuallearning.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticlePart {

    private int id;
    private int articleId;
    private int sequenceNumber;
    private String text;
    private String type;
}
