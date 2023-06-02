package ua.nure.illiashenko.mutuallearning.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.illiashenko.mutuallearning.entity.ArticlePart;

@Repository
public interface ArticlePartRepository extends JpaRepository<ArticlePart, Integer> {

    List<ArticlePart> findByArticleId(Integer articleId);
}
