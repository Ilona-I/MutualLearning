package ua.nure.illiashenko.mutuallearning.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.illiashenko.mutuallearning.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByArticleIdOrderByCreationDateTimeDesc(Integer articleId);
}
