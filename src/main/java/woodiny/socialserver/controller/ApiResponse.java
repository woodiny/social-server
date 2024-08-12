package woodiny.socialserver.controller;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ApiResponse<T> {
    private final boolean success;
    private final T data;
    private final String message;
    private final int status;
    private final LocalDateTime timestamp;

    public ApiResponse(boolean success, T data, String message, int status) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ApiResponse<T> OK(T data, String message) {
        return new ApiResponse<>(true, data, message, HttpStatus.OK.value());
    }

    public static <T> ApiResponse<T> ERROR(String message, HttpStatus httpStatus) {
        return new ApiResponse<>(false, null, message, httpStatus.value());
    }
}
