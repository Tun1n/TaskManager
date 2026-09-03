package dio.taskmanager.infrastructure.repository.http.request;

import dio.taskmanager.application.input.CreateTaskInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public record CreateTaskRequest(
        @NotBlank(message = "Nome não pode ser vazio")
        @Size(min = 3, max = 100, message = "Mínimo de 3 caracteres e máximo de 100 caracteres")
        String title,

        @Size(max = 500, message = "Máximo de 100 caracteres")
        String description) {
    public CreateTaskInput toInput(){
        return new CreateTaskInput(title.trim(), Optional.ofNullable(description.trim()));
    }
}
