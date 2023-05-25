package ua.nure.illiashenko.mutuallearning.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentPart {

    private int id;
    private int commentId;
    private int sequenceNumber;
    private String text;
    private String link;
    private String type;
}
