package ua.nure.illiashenko.mutuallearning.dto.article;

import lombok.Builder;
import lombok.Data;
import ua.nure.illiashenko.mutuallearning.constants.ArticlePartType;

@Data
@Builder
public class ArticlePartResponse {

    private int id;
    private int sequenceNumber;
    private String text;
    private String link;
    private String type;
}
