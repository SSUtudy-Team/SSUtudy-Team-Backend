package SSU.SSUtudyWith.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserLoginResponseDto {

    private Long userId;
    private String token;
}
