package SSU.SSUtudyWith.controller;

import SSU.SSUtudyWith.common.ApiResponse;
import SSU.SSUtudyWith.domain.User;
import SSU.SSUtudyWith.dto.category.CategoryResponseDto;
import SSU.SSUtudyWith.dto.study.StudyOwnResponseDto;
import SSU.SSUtudyWith.dto.study.StudyRequestDto;
import SSU.SSUtudyWith.dto.study.StudyJoinResponseDto;
import SSU.SSUtudyWith.service.CategoryService;
import SSU.SSUtudyWith.service.StudyService;
import SSU.SSUtudyWith.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/studies")
public class StudyController {

    private final StudyService studyService;
    private final UserService userService;
    private final CategoryService categoryService;

    /**
     * 스터디 만들 때 카테고리 넘거주기
     */
    @GetMapping("/make")
    public ResponseEntity getCategories() {
        List<CategoryResponseDto> allCategories = categoryService.getAllCategories();

        return ResponseEntity.ok().body(new Result(allCategories));
    }


    /**
     * 스터디 생성
     * user id 따로 전달 안하고 만드는 방법 없나? -> 클라이언트한테 물어보기
     * 생성된 study id 전달? 아니면 상태만 전달?
     */
    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse> makeStudy(@RequestBody StudyRequestDto request, @PathVariable("userId") Long userId) {
        User findUser = userService.find(userId);
        Long id = studyService.makeStudy(request, findUser);
        return ResponseEntity.ok(ApiResponse.success("스터디 생성 성공", id));
    }


    /**
     * 스터디 조회
     */
    @GetMapping("/{studyId}")
    public ResponseEntity<ApiResponse> getStudy(@PathVariable("studyId") Long studyId) {
        StudyOwnResponseDto response = studyService.search(studyId);
        return ResponseEntity.ok(ApiResponse.success("스터디 조회 성공", response));
    }


    /**
     * 스터디 삭제
     */
    @DeleteMapping("/delete/{studyId}")
    public ResponseEntity<ApiResponse> deleteStudy(@PathVariable("studyId") Long studyId) {
        studyService.delete(studyId);
        return ResponseEntity.ok(ApiResponse.success("스터디 삭제 성공"));
    }


    /**
     * 스터디 수정 -> 어디까지 수정 가능?
     */

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T categoryList;
    }
}

