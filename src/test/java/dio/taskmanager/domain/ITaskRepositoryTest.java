package dio.taskmanager.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class ITaskRepositoryTest {
    ITaskRepository repository;
    protected abstract ITaskRepository createRepository();

    @BeforeEach
    void setUp(){
        this.repository = createRepository();
    }

    // BDD
    @Test
    void should_save_and_retrieve_task_by_id(){
        // given
        var task = new Task("Passar na padaria", Optional.empty());

        // when
        var saved = repository.save(task);
        Optional<Task> result = repository.findById(saved.getId());

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(task.getId());
        assertThat(result.get().getDescription()).isEqualTo(task.getDescription());
        assertThat(result.get().getStatus()).isEqualTo(task.getStatus());
    }

    @Test
    void should_find_all_persisted_tasks(){
        // given
        var task1 = new Task("Ir ao mercado", Optional.of("Comprar limão"));
        var task2 = new Task("Ir para a academia", Optional.of("Treinar"));

        repository.save(task1);
        repository.save(task2);

        // when
        List<Task> tasks = repository.findAll();

        // then
        assertThat(tasks).hasSize(2);
        assertThat(tasks).extracting(Task::getId).containsExactlyInAnyOrder(task1.getId(), task2.getId());
    }

    @Test
    void shoul_delete_task_by_id(){
        // given
        var task = repository.save(new Task("Caminhar", Optional.of("Andar com minha mãe")));
        var taskId = task.getId();

        // when
        repository.delete(taskId);
        Optional<Task> taskDeleted = repository.findById(taskId);

        // then
        assertThat(taskDeleted).isEmpty();
    }

    @Test
    void should_return_empty_when_searching_non_existent_task(){
        // given
        var nonExistentId = new TaskId();

        // when
        Optional<Task> result = repository.findById(nonExistentId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void should_update_task_status_successfully(){
        // given
        var task = new Task("Jogar futebol", Optional.empty());
        repository.save(task);

        task.setDescription(Optional.of("Vou jogar bola às 15:30"));
        task.setStatus(TaskStatus.IN_PROGRESS);

        // when
        repository.save(task);
        Optional<Task> result = repository.findById(task.getId());

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getDescription()).isEqualTo(Optional.of("Vou jogar bola às 15:30"));
        assertThat(result.get().getStatus()).isEqualTo(TaskStatus.IN_PROGRESS);

    }
}