package dio.taskmanager.application;

import dio.taskmanager.domain.ITaskRepository;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.domain.TaskNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskUseCase {
    private final ITaskRepository repository;

    public DeleteTaskUseCase(ITaskRepository repository) {
        this.repository = repository;
    }

    public void execute(TaskId id){
        if(repository.findById(id).isEmpty()){
            throw new TaskNotFoundException(id);
        }

        repository.delete(id);
    }
}
