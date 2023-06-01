package SSU.SSUtudyWith.dto.study;

import SSU.SSUtudyWith.dto.category.CategoryCodeDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StudyRequestDto {
    //@NotBlank(message = "학과 / 과목명을 입력해주세요.")
    private String college;

    private String department;

    private String className;


    //@NotBlank(message = "제목을 입력해주세요")
    private String title;

    //@NotBlank(message = "내용을 입력해주세요")
    private String content;

    //@NotBlank(message = "참가인원을 입력해주세요")
    private int userCount;

    private String roomLink;

    private List<CategoryCodeDto> categoryCodeDtos;

}
