package SSU.SSUtudyWith.dto.user;

import SSU.SSUtudyWith.dto.category.CategoryCodeDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class UserForm {
    @NotBlank(message = "이름을 입력 해주세요.")
    private String name;

    @NotBlank(message = "카테고리를 선택 해주세요.")
    private List<CategoryCodeDto> categoryCodeDtos;

}
