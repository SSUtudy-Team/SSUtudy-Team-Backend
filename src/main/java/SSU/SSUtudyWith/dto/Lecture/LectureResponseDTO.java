package SSU.SSUtudyWith.dto.Lecture;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@NoArgsConstructor
@Data
public class LectureResponseDTO {

    private Map<String,Map<String,Map<String, Set<String>>>> IT_Department= new HashMap<>();




}