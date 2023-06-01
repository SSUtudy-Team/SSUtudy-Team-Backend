package SSU.SSUtudyWith.dto.user;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@Data
public class UserLogInDto {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String studentId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    public UserLogInDto(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;

    }
}