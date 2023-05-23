package ua.nure.illiashenko.mutuallearning.dto.article;

import lombok.Builder;
import lombok.Data;
import ua.nure.illiashenko.mutuallearning.entity.Mark;

@Data
@Builder
public class ArticleForUpdateResponse {

    private int id;
    private String title;
    private String type;
    private Mark[] marks;
    private ArticlePartResponse[] articleParts;
}
