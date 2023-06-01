package SSU.SSUtudyWith.repository;

import SSU.SSUtudyWith.domain.Study;
import SSU.SSUtudyWith.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyRepository extends JpaRepository<Study, Long> {

}
