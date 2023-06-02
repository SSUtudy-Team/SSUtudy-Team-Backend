package SSU.SSUtudyWith.dto.user;

import SSU.SSUtudyWith.dto.category.CategoryCodeDto;
import lombok.*;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Data
public class UserJoinDto {
    @NotBlank(message = "학번을 입력해주세요.")
    @Pattern(message = "숫자만 입력해주세요.", regexp="[0-9]+")
    @Size(message = "학번은 8자리입니다.", min = 8, max = 8)
    private String studentId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(message = "비밀번호는 8자리 이상 15자리 이하입니다.", min = 8, max = 15)
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotNull(message = "학년을 선택해주세요")
    private int grade;

    @NotBlank(message = "학과를 선택해주세요.")
    private String department;

   @NotNull(message = "관심 카테고리를 선택해주세요.")
   @Size(message = "3개 이상의 카테고리를 선택해주세요.", min = 3)
    private List<CategoryCodeDto> categoryCodeDtos;


    @Builder
    public UserJoinDto(String studentId, String password, String name, int grade, String department, List<CategoryCodeDto> categoryCodeDtos) {
        this.studentId = studentId;
        this.password = password;
        this.name = name;
        this.grade = grade;
        this.department = department;
        this.categoryCodeDtos = categoryCodeDtos;
    }
}