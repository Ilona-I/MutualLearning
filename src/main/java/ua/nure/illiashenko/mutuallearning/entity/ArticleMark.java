package ua.nure.illiashenko.mutuallearning.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleMark {

    private int id;
    private int articleId;
    private int markId;
}
