package dio.taskmanager.application;

import dio.taskmanager.application.input.CreateTaskInput;
import dio.taskmanager.application.output.CreateTaskOutput;
import dio.taskmanager.domain.ITaskRepository;
import dio.taskmanager.domain.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateTaskUseCaseTest {
    @Mock
    ITaskRepository repository;

    @InjectMocks
    CreateTaskUseCase useCase;

    @Test
    void should_create_task_successfully(){
        // given
        var input = new CreateTaskInput("Estudar java", Optional.of("Estou na DIO"));

        when(repository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        CreateTaskOutput taskOutput = useCase.execute(input);

        // then
        assertNotNull(taskOutput);
        assertNotNull(taskOutput.id());
        assertEquals("Estudar java", taskOutput.title());
        assertEquals(Optional.of("Estou na DIO"), taskOutput.description());

        verify(repository, times(1)).save(any(Task.class));
    }

}