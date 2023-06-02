package SSU.SSUtudyWith.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class UserFindDto {

    private String studentId;

    private String password;


    private String name;

    private int grade;

    private String department;

    private List<String> categoryList;

}
