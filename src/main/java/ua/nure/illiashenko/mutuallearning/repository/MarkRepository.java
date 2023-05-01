package ua.nure.illiashenko.mutuallearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.illiashenko.mutuallearning.entity.Mark;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer> {

}
