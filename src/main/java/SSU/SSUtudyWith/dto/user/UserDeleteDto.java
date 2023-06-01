package SSU.SSUtudyWith.dto.user;

import lombok.*;

import javax.validation.constraints.NotBlank;


@NoArgsConstructor
@Data
public class UserDeleteDto {

    @NotBlank
    private Long userId;

    @NotBlank(message = "비밀번호를 입력하세요.")
    private String password;

}