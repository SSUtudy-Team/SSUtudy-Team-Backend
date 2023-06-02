package SSU.SSUtudyWith.common.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Data
@Getter
@Builder
public class ApiExceptionResponse {
    private int code;
    private String field;
    private HttpStatus httpStatus;
    private String errorMessage;

    public static ApiExceptionResponse fail(int code, HttpStatus httpStatus, String message) {
        return ApiExceptionResponse.builder()
                .code(code)
                .httpStatus(httpStatus)
                .errorMessage(message)
                .build();
    }

    public static ApiExceptionResponse fail(int code, String field, HttpStatus httpStatus, String message) {
        return ApiExceptionResponse.builder()
                .code(code)
                .field(field)
                .httpStatus(httpStatus)
                .errorMessage(message)
                .build();
    }

}
