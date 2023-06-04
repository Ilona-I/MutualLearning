package ua.nure.illiashenko.mutuallearning.repository;

import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.nure.illiashenko.mutuallearning.constants.ArticleType;
import ua.nure.illiashenko.mutuallearning.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> findAllByIdIsInAndTypeIsInAndTitleContainsOrderByCreationDateTimeDesc(Set<Integer> articlesId,
        Set<String> types, String title, Pageable limit);

    List<Article> findAllByIdIsInAndTypeIsInOrderByCreationDateTimeDesc(Set<Integer> articlesId, Set<String> types,
        Pageable limit);

    @Modifying
    @Query( value = "SELECT article.* "
        + " FROM article LEFT JOIN user_article ON article.id = user_article.article_id WHERE user_article.reaction='LIKE'"
        + " AND article.id in ?1 AND article.type in ?2 AND article.title LIKE CONCAT('%', ?3,'%') "
        + " GROUP BY article.id "
        + " ORDER BY COUNT(user_article.reaction) DESC "
        + " LIMIT ?4, ?5 ",  nativeQuery = true)
    List<Article> findAllByTitleAndByPopularity(Set<Integer> articlesId, Set<String> type, String title, int page,
        int size);
}
