package it.epicode.w6d5.exception;

import lombok.Data;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ErrorResponse {
    private int status;
    private Object message;
    private LocalDateTime dataResponse;

    public ErrorResponse(int status, Object message){
        this.status = status;
        this.message = message;
        dataResponse = LocalDateTime.now();
    }

    public static String handleValidationMessages(BindingResult bindingResult){
        return bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(","));
    }
}
