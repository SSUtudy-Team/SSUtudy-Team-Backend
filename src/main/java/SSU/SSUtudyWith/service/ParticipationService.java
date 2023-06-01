package SSU.SSUtudyWith.service;

import SSU.SSUtudyWith.domain.Participation;
import SSU.SSUtudyWith.domain.Study;
import SSU.SSUtudyWith.domain.User;
import SSU.SSUtudyWith.repository.ParticipationRepository;
import SSU.SSUtudyWith.repository.StudyRepository;
import SSU.SSUtudyWith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final UserRepository userRepository;
    private final StudyRepository studyRepository;

    /**
     * 스터디 참여
     */
    public boolean createParticipation(Long studyId, Long userId) {

        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        Study findStudy = studyRepository.findById(studyId)
                .orElseThrow(() -> new EntityNotFoundException("해당 스터디가 존재하지 않습니다."));

        participationRepository.save(new Participation(findUser, findStudy));
        return true;
    }


    /**
     * 스터디 나가기
     */
    public boolean deleteParticipation(Long studyId, Long userId) {

        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        Study findStudy = studyRepository.findById(studyId)
                .orElseThrow(() -> new EntityNotFoundException("해당 스터디가 존재하지 않습니다."));

        Participation findParticipation = participationRepository.findByUserAndStudy(findUser, findStudy);

        findUser.getParticipations().remove(findParticipation);
        findStudy.getParticipations().remove(findParticipation);
        participationRepository.deleteByUserAndStudy(findUser, findStudy);

        return false;
    }


}
