package ua.nure.illiashenko.mutuallearning.dto.article;

import lombok.Builder;
import lombok.Data;
import ua.nure.illiashenko.mutuallearning.constants.ArticlePartType;

@Data
@Builder
public class ArticleFileLinksResponse {

    private int id;
    private String type;
    private String link;
}
