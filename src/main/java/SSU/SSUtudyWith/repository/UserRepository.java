package SSU.SSUtudyWith.repository;

import SSU.SSUtudyWith.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 학번으로 조회
    Optional<User> findByStudentId(String StudentId);

    void delete(User user);


}
