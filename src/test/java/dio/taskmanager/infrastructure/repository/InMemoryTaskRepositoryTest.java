package dio.taskmanager.infrastructure.repository;
import dio.taskmanager.domain.ITaskRepository;
import dio.taskmanager.domain.ITaskRepositoryTest;

class InMemoryTaskRepositoryTest extends ITaskRepositoryTest {
    @Override
    protected ITaskRepository createRepository() {
        return new InMemoryTaskRepository();
    }
}