package dio.taskmanager.infrastructure.repository;

import dio.taskmanager.domain.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GlobalExceptionHandler {
    public String handleTaskNotFoundException(TaskNotFoundException ex){
        return ex.getMessage();
    }
}
