package ua.nure.illiashenko.mutuallearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.illiashenko.mutuallearning.entity.UserArticle;

@Repository
public interface UserArticleRepository extends JpaRepository<UserArticle, Integer> {

    UserArticle findByUserLoginAndArticleId(String login, Integer articleId);
}
