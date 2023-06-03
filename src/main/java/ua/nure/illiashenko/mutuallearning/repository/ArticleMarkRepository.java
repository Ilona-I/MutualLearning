package ua.nure.illiashenko.mutuallearning.repository;

import java.util.List;
import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.illiashenko.mutuallearning.entity.ArticleMark;

@Repository
public interface ArticleMarkRepository extends JpaRepository<ArticleMark, Integer> {

    List<ArticleMark> findByArticleId(int articleId);

    List<ArticleMark> findAllByMarkIdIsIn(List<Integer> markId);

    void deleteByMarkIdAndArticleId(Integer markId, Integer articleId);
}
