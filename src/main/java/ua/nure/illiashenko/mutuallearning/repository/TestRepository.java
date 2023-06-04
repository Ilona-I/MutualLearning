package ua.nure.illiashenko.mutuallearning.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.illiashenko.mutuallearning.entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {

    List<Test> findByArticleId(Integer articleId);
}
