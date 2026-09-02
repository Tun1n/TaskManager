package dio.taskmanager.application;

import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.domain.ITaskRepository;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.domain.TaskNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetTaskByIdUseCase {
    private final ITaskRepository repository;

    public GetTaskByIdUseCase(ITaskRepository repository) {
        this.repository = repository;
    }

    public TaskOutput execute(TaskId id){
        return repository.findById(id).map(TaskOutput::from).orElseThrow(() -> new TaskNotFoundException(id));
    }
}
