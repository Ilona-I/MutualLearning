package ua.nure.illiashenko.mutuallearning.dto.article;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleRequest {

    private String userLogin;
    private String title;
    private String type;
    private ArticlePartRequest[] articleParts;
    private int[] marksId;
}
