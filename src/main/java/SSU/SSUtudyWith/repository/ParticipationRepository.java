package SSU.SSUtudyWith.repository;

import SSU.SSUtudyWith.domain.Participation;
import SSU.SSUtudyWith.domain.Study;
import SSU.SSUtudyWith.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    // 참여한 스터디 조회
    Participation findByUserAndStudy(User user, Study study);

    // 삭제
    void deleteByUserAndStudy(User user, Study study);
}
