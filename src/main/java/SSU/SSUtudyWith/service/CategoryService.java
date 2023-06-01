package SSU.SSUtudyWith.service;

import SSU.SSUtudyWith.domain.Category;
import SSU.SSUtudyWith.dto.category.CategoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    public List<CategoryResponseDto> getAllCategories() {
        return Arrays.stream(Category.values()).map(CategoryResponseDto::of).collect(Collectors.toList());
    }

}
