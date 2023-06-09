package SSU.SSUtudyWith.controller;

import SSU.SSUtudyWith.common.ApiResponse;
import SSU.SSUtudyWith.dto.category.CategoryScoreDto;
import SSU.SSUtudyWith.dto.study.StudyRankResponseDto;
import SSU.SSUtudyWith.dto.user.*;
import SSU.SSUtudyWith.dto.study.StudyOwnResponseDto;
import SSU.SSUtudyWith.dto.study.StudyJoinResponseDto;
import SSU.SSUtudyWith.service.StudyService;
import SSU.SSUtudyWith.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final StudyService studyService;


    /**
     * 유저 생성
     */
    @PostMapping("/api/v1/users")
    public ResponseEntity<ApiResponse> join(@RequestBody @Valid UserJoinDto userJoinDto) {
        Long id= userService.join(userJoinDto);
        return ResponseEntity.ok(ApiResponse.success("유저 가입 성공", id));
    }

    /**
     * 유저 로그인
     */
    @PostMapping("/api/v1/users/login")
    public ResponseEntity<ApiResponse> login(@RequestBody @Valid UserLogInDto userRequestDto) {
        UserLoginResponseDto dto = userService.login(userRequestDto);
        return ResponseEntity.ok(ApiResponse.success("로그인 성공", dto));
    }

    /**
     * 유저 조회
     */
    @GetMapping("/api/v1/users/{userId}")
    public ResponseEntity<ApiResponse> findUser(@PathVariable("userId") Long userId) {
        UserFindDto dto = userService.search(userId);
        return ResponseEntity.ok(ApiResponse.success("유저 조회 성공", dto));
    }

    /**
     * 유저 정보 업데이트
     */
    @PutMapping("/api/v1/users/{userId}")
    public ResponseEntity<ApiResponse> update(@RequestBody @Valid UserUpdateDto userUpdateDto, @PathVariable("userId") Long userId){

        userService.update(userId, userUpdateDto);
        return ResponseEntity.ok(ApiResponse.success("수정되었습니다!"));
    }

    /**
     * 유저 삭제
     */
    @DeleteMapping("/api/v1/users")
    public ResponseEntity<ApiResponse> delete(@RequestBody @Valid UserDeleteDto userDeleteDto) {

        userService.delete(userDeleteDto);
        return ResponseEntity.ok(ApiResponse.success("삭제 성공"));
    }

    /**
     * 내가 만든 스터디 조회
     */
    @GetMapping("/api/v1/users/own/{userId}")
    public ResponseEntity<ApiResponse> myStudy(@PathVariable("userId") Long userId) {

        List<StudyOwnResponseDto> ownStudies = userService.getMyStudy(userId);
        return ResponseEntity.ok(ApiResponse.success("내가 만든 스터디 목록 조회 성공", ownStudies));
    }


    /**
     *  내가 참여한 스터디 조회
     */
    @GetMapping("/api/v1/users/join/{userId}")
    public Result joinStudy(@PathVariable("userId") Long userId) {
        List<StudyJoinResponseDto> joinStudies = userService.getJoinStudy(userId);

        return new Result(joinStudies);
    }

    /**
     * 추천 스터디 출력
     */
    @GetMapping("/api/v1/users/recommend/{userId}")
    public Result recommendStudy(@PathVariable("userId") Long userId) {
        List<CategoryScoreDto> top10Study = userService.getTop10Study(userId);
        List<StudyRankResponseDto> rankDtoList = new ArrayList<>();

        List<StudyRankResponseDto> dtoList = top10Study.stream()
                .map(categoryScoreDto -> categoryScoreDto.getStudyId())
                .map(i -> studyService.getStudy(i))
                .map(StudyRankResponseDto::create)
                .collect(Collectors.toList());

        return new Result(dtoList);
    }

    /**
     * 메인 화면 출력 -> 내가 참여한 스터디 + 유저 별 추천 스터디
     */
    @GetMapping("/api/v1/users/home/{userId}")
    public ResponseEntity<ApiResponse> mainPage(@PathVariable("userId") Long userId) {

        List<StudyJoinResponseDto> joinStudies = userService.getJoinStudy(userId);

        List<CategoryScoreDto> top10Study = userService.getTop10Study(userId);
        List<StudyRankResponseDto> rankDtoList = new ArrayList<>();

        List<StudyRankResponseDto> dtoList = top10Study.stream()
                .map(categoryScoreDto -> categoryScoreDto.getStudyId())
                .map(i -> studyService.getStudy(i))
                .map(StudyRankResponseDto::create)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.success("메인 화면 출력 성공", new ResultHome(joinStudies, dtoList)));
    }



//    @PostMapping("/authtest")
//    public String authtest() {
//        return "auth success";
//    }
//

    //--데이터 가공 메소드--//
    @Data
    @AllArgsConstructor
    static class ResultHome<T> {
        private T joinStudy;
        private T recommendStudy;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}