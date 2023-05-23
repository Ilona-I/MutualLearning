package ua.nure.illiashenko.mutuallearning.dto.article;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import ua.nure.illiashenko.mutuallearning.entity.Article;

@Data
@Builder
public class QuestionsResponse {

    private List<Article> questions;
}
