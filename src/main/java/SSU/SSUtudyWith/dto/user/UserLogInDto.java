package SSU.SSUtudyWith.dto.user;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@Data
public class UserLogInDto {
    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(message = "숫자만 입력해주세요.", regexp="[0-9]+")
    @Size(message = "학번은 8자리입니다.", min = 8, max = 8)
    private String studentId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(message = "비밀번호는 8자리 이상 15자리 이하입니다.", min = 8, max = 15)
    private String password;

    public UserLogInDto(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }
}