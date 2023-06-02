package SSU.SSUtudyWith.dto.study;

import SSU.SSUtudyWith.dto.category.CategoryCodeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class StudyRequestDto {
    @NotBlank(message = "단과대를 선택해주세요.")
    private String college;

   @NotBlank(message = "학과를 선택해주세요.")
    private String department;

    @NotBlank(message = "과목명을 입력해주세요.")
    private String className;

    @NotBlank(message = "스터디 제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "스터디 내용을 입력해주세요")
    private String content;

    @NotNull(message = "최대 참가 인원을 선택 해주세요")
    private int userCount;

    @NotBlank(message = "오픈채팅 링크를 입력해주세요.")
    private String roomLink;

    @NotNull(message = "관심 카테고리를 선택해주세요.")
    @Size(min = 3, message = "3개 이상의 카테고리를 선택해주세요.")
    private List<CategoryCodeDto> categoryCodeDtos;

}
