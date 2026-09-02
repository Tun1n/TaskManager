package dio.taskmanager.application.output;

import dio.taskmanager.domain.Task;

import java.util.Optional;

public record CreateTaskOutput(String id, String title, Optional<String> description, String status) {
    public static CreateTaskOutput from(Task task){
        return new CreateTaskOutput(
                task.getId().id().toString(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().name());
    }
}
