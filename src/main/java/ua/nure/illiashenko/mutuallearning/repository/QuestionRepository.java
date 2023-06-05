package ua.nure.illiashenko.mutuallearning.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.illiashenko.mutuallearning.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByTestId(Integer testId);
}
