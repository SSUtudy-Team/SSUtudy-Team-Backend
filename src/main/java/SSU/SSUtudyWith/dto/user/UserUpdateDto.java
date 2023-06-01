package SSU.SSUtudyWith.dto.user;

import SSU.SSUtudyWith.dto.category.CategoryCodeDto;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@Data
public class UserUpdateDto {
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "학년을 입력해주세요")
    private Integer grade;

    @NotBlank(message = "학과를 선택해주세요.")
    private String department;

    @NotBlank(message = "관심 카테고리를 선택해주세요.")
    private List<CategoryCodeDto> categoryCodeDtoList = new ArrayList<>();


    public UserUpdateDto(String password, String name, Integer grade, String department, List<CategoryCodeDto> categoryCodeDtoList) {
        this.password = password;
        this.name = name;
        this.grade = grade;
        this.department = department;
        this.categoryCodeDtoList = categoryCodeDtoList;
    }

}