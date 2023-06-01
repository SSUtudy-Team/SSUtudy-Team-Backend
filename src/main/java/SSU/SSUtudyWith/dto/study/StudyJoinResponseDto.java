package SSU.SSUtudyWith.dto.study;

import SSU.SSUtudyWith.domain.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Builder
public class StudyJoinResponseDto {
    @NotBlank(message = "제목을 입력해주세요")
    private String title; // 제목만 출력

}
