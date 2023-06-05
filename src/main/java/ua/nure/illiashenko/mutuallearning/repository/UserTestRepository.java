package ua.nure.illiashenko.mutuallearning.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.illiashenko.mutuallearning.entity.UserTest;

@Repository
public interface UserTestRepository extends JpaRepository<UserTest, Integer> {

    List<UserTest> findByTestIdAndUserLogin(Integer testId, String userLogin);

    List<UserTest> findByTestIdOrderByUserLoginAscDateTimeDesc(Integer testId);
}
