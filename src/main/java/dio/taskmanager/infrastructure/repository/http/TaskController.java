package dio.taskmanager.infrastructure.repository.http;

import dio.taskmanager.application.CreateTaskUseCase;
import dio.taskmanager.application.DeleteTaskUseCase;
import dio.taskmanager.application.GetTaskByIdUseCase;
import dio.taskmanager.application.GetTaskUseCase;
import dio.taskmanager.application.UpdateTaskUseCase;
import dio.taskmanager.application.input.CreateTaskInput;
import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.infrastructure.repository.http.request.CreateTaskRequest;
import dio.taskmanager.infrastructure.repository.http.request.UpdateTaskRequest;
import dio.taskmanager.infrastructure.repository.http.response.TaskResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final CreateTaskUseCase createTaskUseCase;
    private final GetTaskUseCase getTaskUseCase;
    private final GetTaskByIdUseCase getTaskByIdUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase, GetTaskUseCase getTaskUseCase, GetTaskByIdUseCase getTaskByIdUseCase, DeleteTaskUseCase deleteTaskUseCase, UpdateTaskUseCase updateTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.getTaskUseCase = getTaskUseCase;
        this.getTaskByIdUseCase = getTaskByIdUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    TaskResponse create(@RequestBody CreateTaskRequest request){
        var input = request.toInput();
        var output = createTaskUseCase.execute(input);
        return TaskResponse.from(output);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TaskResponse> get(){
        return getTaskUseCase.execute().stream().map(TaskResponse::from).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskResponse getById(@PathVariable UUID id){
        var output = getTaskByIdUseCase.execute(new TaskId(id));
        return TaskResponse.from(output);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID id){
        deleteTaskUseCase.execute(new TaskId(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    TaskResponse patchUpdate(@PathVariable UUID id, @RequestBody UpdateTaskRequest request){
        var input = request.toInput();
        var output = updateTaskUseCase.execute(new TaskId(id), input);
        return TaskResponse.from(output);
    }
}
