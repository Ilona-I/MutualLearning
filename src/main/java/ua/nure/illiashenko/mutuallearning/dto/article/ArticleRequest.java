package ua.nure.illiashenko.mutuallearning.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {

    private String title;
    private String type;
    private ArticlePartRequest[] articleParts;
    private String[] marksId;
}
