package dio.taskmanager.application;

import dio.taskmanager.application.input.CreateTaskInput;
import dio.taskmanager.application.output.CreateTaskOutput;
import dio.taskmanager.domain.ITaskRepository;
import dio.taskmanager.domain.Task;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskUseCase {
    private final ITaskRepository taskRepository;
    public CreateTaskUseCase(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public CreateTaskOutput execute(CreateTaskInput input){
        var task = new Task(input.title(), input.description());
        var saved = taskRepository.save(task);
        return CreateTaskOutput.from(saved);
    }
}
