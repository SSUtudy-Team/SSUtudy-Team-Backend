package SSU.SSUtudyWith.dto.study;

import SSU.SSUtudyWith.domain.Category;
import SSU.SSUtudyWith.domain.StudyStatus;
import SSU.SSUtudyWith.domain.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Builder
public class StudyOwnResponseDto {

    private Long studyId;
    private Subject subject;    // 단과대, 학부, 과목명

    private String title;   // 스터디 제목

    private String content; // 스터디 내용

    private int userCount;  // 최대 참여 인원

    private int curUserCount;   // 현재 참여 인원

    private String roomLink; // 톡방 링크

    //private StudyStatus studyStatus;    // 스터디방 상태 -> 모집중, 모집완료, 종료
    private String studyStatus;

    private List<String> categoryList;    // 스터디 방 카테고리

    private List<String> userName;  // 스터디 방 참여 유저 이름

    private boolean joinOrNot; // 참여여뷰

}
