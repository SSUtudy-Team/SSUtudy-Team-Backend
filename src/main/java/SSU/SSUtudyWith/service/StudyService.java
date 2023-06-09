package SSU.SSUtudyWith.service;

import SSU.SSUtudyWith.domain.*;
import SSU.SSUtudyWith.dto.category.CategoryScoreDto;
import SSU.SSUtudyWith.dto.study.StudyOwnResponseDto;
import SSU.SSUtudyWith.dto.study.StudyRequestDto;
import SSU.SSUtudyWith.repository.CategoryStudyRepository;
import SSU.SSUtudyWith.repository.ParticipationRepository;
import SSU.SSUtudyWith.repository.StudyRepository;
import SSU.SSUtudyWith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;

    private final UserRepository userRepository;
    private final ParticipationRepository participationRepository;
    private final CategoryStudyRepository categoryStudyRepository;

    /**
     * 스터디 생성
     */
    @Transactional
    public Long makeStudy(StudyRequestDto requestDto, User user) {
        Subject subject = new Subject(requestDto.getCollege(), requestDto.getDepartment(), requestDto.getClassName());


        List<Category> categoryList = requestDto.getCategoryCodeDtos().stream()
                .map(categoryCodeDto -> Category.valueOf(categoryCodeDto.getCategoryCode()))
                .collect(Collectors.toList());

        Study study = new Study(subject, requestDto.getTitle(),
                requestDto.getContent(), requestDto.getUserCount(), requestDto.getRoomLink(), user);


        for (Category category : categoryList) {
            CategoryStudy categoryStudy = new CategoryStudy(study, category);
            categoryStudyRepository.save(categoryStudy);
            study.getCategoryStudies().add(categoryStudy);
        }

        // 자기자신 포함
        participationRepository.save(new Participation(user, study));

        Study saveStudy = studyRepository.save(study);
        return saveStudy.getId();
    }

    /**
     * 스터디 조회 (단일 조회)
     */
    public StudyOwnResponseDto search(Long studyId, Long userId) {
        Study findStudy = studyRepository.findById(studyId)
                .orElseThrow(() -> new EntityNotFoundException("해당 스터디가 존재하지 않습니다."));

        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        List<String> categoryList = findStudy.getCategoryStudies().stream()
                .map(categoryStudy -> categoryStudy.getCategory())
                .map(category -> category.getName())
                .collect(Collectors.toList());

        List<Participation> participationList = findStudy.getParticipations();

        List<String> userList = participationList.stream()
                .map(participation -> participation.getUser())
                .map(user -> user.getName())
                .collect(Collectors.toList());

        return StudyOwnResponseDto.builder()
                .studyId(findStudy.getId())
                .subject(findStudy.getSubject())
                .title(findStudy.getTitle())
                .content(findStudy.getContent())
                .userCount(findStudy.getUserCount())
                .curUserCount(participationList.size())
                .roomLink(findStudy.getRoomLink())
                .studyStatus(findStudy.getStatus().getStatus())
                .categoryList(categoryList)
                .userName(userList)
                .joinOrNot(userList.contains(findUser.getName()))
                .mineOrNot(findStudy.getUser().equals(findUser))
                .build();
    }

    public Study getStudy(Long studyId) {
        Study findStudy = studyRepository.findById(studyId)
                .orElseThrow(() -> new EntityNotFoundException("해당 스터디가 존재하지 않습니다."));

        return findStudy;
    }

    /**
     * 모든 스터디 조회
     */
    public List<Study> findAll() {
        return studyRepository.findAll();
    }


    /**
     * 스터디 시간순 조회
     */
    public List<Study> findAllByTime() {
        List<Study> studyList = studyRepository.findAll();

        //정렬
        Comparator<Study> comparator = (s1, s2) -> s1.getMadeTime().compareTo(s2.getMadeTime());

        Collections.sort(studyList, comparator.reversed());
        return studyList;
    }


    /**
     * 스터디 종료
     */
    @Transactional
    public void end(Long studyId) {
        Study findStudy = studyRepository.findById(studyId)
                .orElseThrow(() -> new EntityNotFoundException("해당 스터디가 존재하지 않습니다."));

        //아마 개설자 확인 필요,,

        //상태 변경
        findStudy.changeStatus(StudyStatus.END);
    }

    /**
     * 스터디 삭제
     */
    @Transactional
    public void delete(Long studyId) {
        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new EntityNotFoundException("해당 스터디가 존재하지 않습니다."));
        studyRepository.delete(study);
    }



    /**
     * 스터디 수정
     */

}
