package SSU.SSUtudyWith.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@Data
public class UserFindDto {

    private String studentId;

    private String password;


    private String name;

    private int grade;

    private String department;

    private List<String> categoryList = new ArrayList<>();


    public UserFindDto(String studentId, String password, String name, int grade, String department, List<String> categoryList) {
        this.studentId = studentId;
        this.password = password;
        this.name = name;
        this.grade = grade;
        this.department = department;
        this.categoryList = categoryList;
    }

}
