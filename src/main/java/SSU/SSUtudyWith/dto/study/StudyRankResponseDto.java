package SSU.SSUtudyWith.dto.study;

import SSU.SSUtudyWith.domain.Study;
import SSU.SSUtudyWith.domain.StudyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StudyRankResponseDto {

    private Long studyId;
    private final String department;
    private final String className;
    private final String title;

    private final String content;

    private final int userCount;  // 최대 참여 인원

    private int curUserCount;   // 현재 참여 인원

    private String studyStatus;    // 스터디방 상태 -> 모집중, 모집완료, 종료

    public static StudyRankResponseDto create(StudyOwnResponseDto dto) {
        return StudyRankResponseDto.builder()
                .studyId(dto.getStudyId())
                .department(dto.getSubject().getDepartment())
                .className(dto.getSubject().getClassName())
                .studyStatus(dto.getStudyStatus())
                .title(dto.getTitle())
                .content(dto.getContent())
                .userCount(dto.getUserCount())
                .curUserCount(dto.getCurUserCount())
                .build();
    }

    public static StudyRankResponseDto create(Study study) {
        return StudyRankResponseDto.builder()
                .studyId(study.getId())
                .department(study.getSubject().getDepartment())
                .className(study.getSubject().getClassName())
                .studyStatus(study.getStatus().getStatus())
                .title(study.getTitle())
                .content(study.getContent())
                .userCount(study.getUserCount())
                .curUserCount(study.getParticipations().size())
                .build();
    }

}

