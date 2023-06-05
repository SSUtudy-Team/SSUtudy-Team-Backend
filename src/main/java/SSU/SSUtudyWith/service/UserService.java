package SSU.SSUtudyWith.service;

import SSU.SSUtudyWith.domain.*;
import SSU.SSUtudyWith.dto.category.CategoryResponseDto;
import SSU.SSUtudyWith.dto.category.CategoryScoreDto;
import SSU.SSUtudyWith.dto.study.StudyOwnResponseDto;
import SSU.SSUtudyWith.dto.study.StudyJoinResponseDto;
import SSU.SSUtudyWith.dto.user.*;
import SSU.SSUtudyWith.repository.CategoryUserRepository;
import SSU.SSUtudyWith.repository.JwtTokenProvider;
import SSU.SSUtudyWith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final CategoryUserRepository categoryUserRepository;
    private final StudyService studyService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    /**
     * 참여한 스터디 조회
     */
    public List<StudyJoinResponseDto> getJoinStudy(Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException());

        List<StudyJoinResponseDto> studyJoinResponseDtos = new ArrayList<>();
        List<Participation> participations = findUser.getParticipations();

        if (!participations.isEmpty()) {
            for (Participation participation : participations) {
                Study findStudy = participation.getStudy();
                studyJoinResponseDtos.add(StudyJoinResponseDto.builder()
                        .title(findStudy.getTitle())
                        .build());
            }
        }
        else {
            throw new EntityNotFoundException("참여한 스터디가 없습니다.");
        }

        return studyJoinResponseDtos;
    }


    /**
     * 내가 만든 스터디 조회
     */
    public List<StudyOwnResponseDto> getMyStudy(Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));

        List<Study> studies = findUser.getStudies();
        List<StudyOwnResponseDto> studyOwnResponseDtoList = new ArrayList<>();

        if (!studies.isEmpty()) {
            for (Study study : studies) {
                List<String> categoryList = study.getCategoryStudies().stream()
                        .map(categoryStudy -> categoryStudy.getCategory().getName())
                        .collect(Collectors.toList());

                studyOwnResponseDtoList.add(StudyOwnResponseDto.builder()
                        .title(study.getTitle())
                        .subject(study.getSubject())
                        .content(study.getContent())
                        .userCount(study.getUserCount())
                        .roomLink(study.getRoomLink())
                        .categoryList(categoryList)
                        .studyStatus(study.getStatus())
                        .build());
            }
        } else {
            throw new EntityNotFoundException("내가 만든 스터디가 없습니다.");
        }

        return studyOwnResponseDtoList;
    }

    /**
     * 유저에게 추천하는 스터디 top10
     */
    public List<CategoryScoreDto> getTop10Study(Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));

        //유저의 카테고리 목록
        List<String> findCategoryList = findUser.getCategoryUsers().stream()
                .map(categoryUser -> categoryUser.getCategory())
                .map(CategoryResponseDto::of)
                .map(categoryResponseDto -> categoryResponseDto.getCode())
                .collect(Collectors.toList());

        List<Study> allStudy = studyService.findAll();
        List<List<String>> allStudyCategoryList = new ArrayList<>();

        //모든 스터디의 카테고리 목록 -> 모든 스터디를 가져오는 기능 필요(service)
        for (Study findStudy : allStudy) {
            List<String> findSingleStudyCategory = findStudy.getCategoryStudies().stream()
                    .map(categoryStudy -> categoryStudy.getCategory())
                    .map(CategoryResponseDto::of)
                    .map(categoryResponseDto -> categoryResponseDto.getCode())
                    .collect(Collectors.toList());
            allStudyCategoryList.add(findSingleStudyCategory);
        }

        List<Double> totalScoreList = new ArrayList<>();

        // 카테고리 비교해서 점수화
        for (List<String> singleList: allStudyCategoryList) {

            List<Double> scoreList = singleList.stream()
                    .filter(s -> findCategoryList.contains(s))
                    .map(s -> Category.valueOf(s).getScore())
                    .collect(Collectors.toList());
            double totalScore = scoreList.stream().mapToDouble(f -> f).sum();

            totalScoreList.add(totalScore);
        }

        List<CategoryScoreDto> scoreDtoList = new ArrayList<>();
        for (int i = 0; i < totalScoreList.size(); i++) {
            scoreDtoList.add(new CategoryScoreDto(allStudy.get(i).getId(), totalScoreList.get(i)));
        }

        // 정렬
        scoreDtoList.sort(Comparator.comparingDouble(CategoryScoreDto::getTotalScore).reversed());

        return scoreDtoList;
    }

    /**
     * 유저 조회
     */
    public UserFindDto search(Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("해당하는 유저가 없습니다."));

        List<String> categoryList = findUser.getCategoryUsers().stream()
                .map(categoryUser -> categoryUser.getCategory())
                .map(category -> category.getName())
                .collect(Collectors.toList());

        return UserFindDto.builder()
                .studentId(findUser.getStudentId())
                .name(findUser.getName())
                .password(findUser.getPassword())
                .grade(findUser.getGrade())
                .department(findUser.getDepartment())
                .categoryList(categoryList)
                .build();
    }

    public User find(Long userId){
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 유저가 없습니다."));
        return findUser;
    }

    /**
     * 유저 회원가입
     */
    @Transactional
    public Long join(UserJoinDto userJoinDto) {

        User newUser = new User(userJoinDto.getStudentId(), passwordEncoder.encode(userJoinDto.getPassword()),userJoinDto.getName(),
                userJoinDto.getGrade(), userJoinDto.getDepartment(), Collections.singletonList("ROLE_USER"));


        List<Category> categoryList = userJoinDto.getCategoryCodeDtos().stream()
                .map(categoryCodeDto -> Category.valueOf(categoryCodeDto.getCategoryCode()))
                .collect(Collectors.toList());

        for (Category category : categoryList) {
            CategoryUser categoryUser = new CategoryUser(newUser, category);
            categoryUserRepository.save(categoryUser);
            newUser.getCategoryUsers().add(categoryUser);
        }


        userRepository.save(newUser);
        System.out.println(newUser.getCategoryUsers());
        return newUser.getId();
    }

    /**
     * 유저 정보 업데이트
     */
    @Transactional
    public void update(Long userId, UserUpdateDto userUpdateDto){

        User updateUser = userRepository.findById(userId)  //find ByStudent_Id로 안해도 됨?
                .orElseThrow(()-> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        // password 인코딩
        String encodePassWord = passwordEncoder.encode(userUpdateDto.getPassword());

        updateUser.updateUser(encodePassWord, userUpdateDto.getName(), userUpdateDto.getGrade(), userUpdateDto.getDepartment());

        // 카테고리 설정은 따로
        updateUser.getCategoryUsers().clear();
        List<Category> categoryList = userUpdateDto.getCategoryCodeDtos().stream()
                .map(dto -> Category.valueOf(dto.getCategoryCode()))
                .collect(Collectors.toList());

        for (Category category : categoryList) {
            CategoryUser categoryUser = new CategoryUser(updateUser, category);
            categoryUserRepository.save(categoryUser);
            updateUser.getCategoryUsers().add(categoryUser);
        }
    }


    /**
     * 유저 삭제
     */
    @Transactional
    public void delete(UserDeleteDto userDeleteDto){
        User deleteUser = userRepository.findById(userDeleteDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        // 비밀번호 일치여부 확인
        if (!passwordEncoder.matches(userDeleteDto.getPassword(), deleteUser.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        userRepository.delete(deleteUser);
    }


    /**
     * 유저 로그인 -> 단순 조회?
     */
    @Transactional
    public String login(UserLogInDto userLogInDto) {

        User findUser = userRepository.findByStudentId(userLogInDto.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        // 비밀번호 일치 여부
        if (!passwordEncoder.matches(userLogInDto.getPassword(), findUser.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        return jwtTokenProvider.createToken(findUser.getStudentId(), findUser.getRoles());
    }
}
