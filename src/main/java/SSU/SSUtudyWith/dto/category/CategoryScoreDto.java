package SSU.SSUtudyWith.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class CategoryScoreDto {
    private Long studyId;
    private Double totalScore;

    public CategoryScoreDto(Long studyId, Double totalScore) {
        this.studyId = studyId;
        this.totalScore = totalScore;
    }
}
