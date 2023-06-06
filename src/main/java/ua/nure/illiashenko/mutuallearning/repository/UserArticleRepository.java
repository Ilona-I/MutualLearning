package ua.nure.illiashenko.mutuallearning.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.illiashenko.mutuallearning.constants.ArticleUserRole;
import ua.nure.illiashenko.mutuallearning.constants.ReactionType;
import ua.nure.illiashenko.mutuallearning.entity.UserArticle;

@Repository
public interface UserArticleRepository extends JpaRepository<UserArticle, Integer> {

    Optional<UserArticle> findByUserLoginAndArticleId(String login, Integer articleId);

    List<UserArticle> findAllByArticleIdAndRoleIsIn(Integer articleId, List<String> roles);

    List<UserArticle> findAllByUserLoginAndRoleIsIn(String userLogin, List<String> roles);

    List<UserArticle> findAllByUserLoginAndReaction(String userLogin, String reactionType);

    List<UserArticle> findAllByUserLoginContains(String userLogin);


}
