package dio.taskmanager.application;

import dio.taskmanager.application.input.UpdateTaskInput;
import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.domain.ITaskRepository;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.domain.TaskNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskUseCase {
    private final ITaskRepository repository;

    public UpdateTaskUseCase(ITaskRepository repository) {
        this.repository = repository;
    }

    public TaskOutput execute(TaskId id, UpdateTaskInput taskInput){
        var task = repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        task.update(taskInput.title(), taskInput.description(), taskInput.status());
        var updatedTask = repository.save(task);
        return TaskOutput.from(updatedTask);
    }
}
