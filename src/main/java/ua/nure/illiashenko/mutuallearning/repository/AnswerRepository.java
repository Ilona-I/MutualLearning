package ua.nure.illiashenko.mutuallearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.illiashenko.mutuallearning.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}
