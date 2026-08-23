package br.com.cmarinho.taskmanager.application;

import br.com.cmarinho.taskmanager.application.input.CreateTaskInput;
import br.com.cmarinho.taskmanager.application.output.TaskOutput;
import br.com.cmarinho.taskmanager.domain.Task;
import br.com.cmarinho.taskmanager.domain.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CreateTaskUseCaseMockTest {

    @Mock
    TaskRepository repository;

    @InjectMocks
    CreateTaskUseCase useCase;

    @Test
    void should_create_task_successfully() {
        //given
        var input = new CreateTaskInput("Learn java", Optional.of("study spring with hibernate"));

        when(repository.save(any(Task.class)))
                .thenAnswer(invocation ->
                    invocation.getArgument(0)
                );

        //when
        TaskOutput output = useCase.execute(input);

        //then
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals("Learn java", output.title());
        assertEquals(Optional.of("study spring with hibernate"), output.description());

        verify(repository, times(1)).save(any(Task.class));
    }
}