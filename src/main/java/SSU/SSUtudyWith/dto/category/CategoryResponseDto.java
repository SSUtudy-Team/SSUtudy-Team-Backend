package SSU.SSUtudyWith.dto.category;

import SSU.SSUtudyWith.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryResponseDto {

    private String code;
    private String name;

    public static CategoryResponseDto of(Category category) {
        return new CategoryResponseDto(category.name(), category.getName());
    }

}
