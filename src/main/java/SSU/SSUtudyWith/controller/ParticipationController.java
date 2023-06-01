package SSU.SSUtudyWith.controller;

import SSU.SSUtudyWith.common.ApiResponse;
import SSU.SSUtudyWith.service.ParticipationService;
import SSU.SSUtudyWith.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ParticipationController {

    private final ParticipationService participationService;

    /**
     * 스터디 참여하기
     */
    @PostMapping("/api/v1/join/{studyId}/{userId}")
    public ResponseEntity<ApiResponse> joinStudy(@PathVariable("studyId") Long studyId, @PathVariable("userId") Long userId) {
        boolean response =  participationService.createParticipation(studyId, userId);
        return ResponseEntity.ok(ApiResponse.success("스터디 참여 성공", response));
    }



    /**
     * 스터디 나가기
     */
    @DeleteMapping("/api/v1/out/{studyId}/{userId}")
    public ResponseEntity<ApiResponse> outStudy(@PathVariable("studyId") Long studyId, @PathVariable("userId") Long userId){
        boolean response = participationService.deleteParticipation(studyId, userId);
        return ResponseEntity.ok(ApiResponse.success("스터디 나가기 성공", response));
    }


}
