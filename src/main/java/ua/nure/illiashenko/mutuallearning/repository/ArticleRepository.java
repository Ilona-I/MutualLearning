package ua.nure.illiashenko.mutuallearning.repository;

import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.illiashenko.mutuallearning.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> findAllByIdIsInAndTypeIsInAndTitleContainsOrderByCreationDateTimeDesc(Set<Integer> articlesId,
        Set<String> types, String title, Pageable limit);

    List<Article> findAllByIdIsInAndTypeIsInOrderByCreationDateTimeDesc(Set<Integer> articlesId, Set<String> types,
        Pageable limit);
}
