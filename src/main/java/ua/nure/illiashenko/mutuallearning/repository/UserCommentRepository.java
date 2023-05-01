package ua.nure.illiashenko.mutuallearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.illiashenko.mutuallearning.entity.UserComment;

@Repository
public interface UserCommentRepository extends JpaRepository<UserComment, Integer> {

}
