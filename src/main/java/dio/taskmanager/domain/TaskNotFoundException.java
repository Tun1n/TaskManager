package dio.taskmanager.domain;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(TaskId id) {
        super("Task not found");
    }
}
