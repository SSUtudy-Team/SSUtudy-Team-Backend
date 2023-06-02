package SSU.SSUtudyWith.dto.user;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@NoArgsConstructor
@Data
public class UserDeleteDto {

    @NotBlank(message = "user_id를 입력해주세요.")
    private Long userId;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(message = "비밀번호는 8자리 이상 15자리 이하입니다.", min = 8, max = 15)
    private String password;

}