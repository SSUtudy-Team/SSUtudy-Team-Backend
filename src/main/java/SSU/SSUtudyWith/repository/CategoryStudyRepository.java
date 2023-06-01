package SSU.SSUtudyWith.repository;

import SSU.SSUtudyWith.domain.CategoryStudy;
import SSU.SSUtudyWith.domain.Participation;
import SSU.SSUtudyWith.domain.Study;
import SSU.SSUtudyWith.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryStudyRepository extends JpaRepository<CategoryStudy, Long> {
    //Participation findByUserAndStudy(User user, Study study);
}
