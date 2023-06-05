package ua.nure.illiashenko.mutuallearning.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.illiashenko.mutuallearning.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    List<Answer> findByQuestionId(Integer questionId);
}
