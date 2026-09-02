package dio.taskmanager.application;

import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.domain.ITaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTaskUseCase {
    private final ITaskRepository repository;
    public GetTaskUseCase(ITaskRepository repository) {
        this.repository = repository;
    }

    public List<TaskOutput> execute()
    {
        return repository.findAll().stream().map(TaskOutput::from).toList();
    }

}
