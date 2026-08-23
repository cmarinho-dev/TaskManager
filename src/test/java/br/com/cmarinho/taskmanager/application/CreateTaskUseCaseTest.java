package br.com.cmarinho.taskmanager.application;

import br.com.cmarinho.taskmanager.application.input.CreateTaskInput;
import br.com.cmarinho.taskmanager.application.output.TaskOutput;
import br.com.cmarinho.taskmanager.domain.InMemoryTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateTaskUseCaseTest {

    @Autowired
    CreateTaskUseCase useCase;

    @Test
    void should_create_task_successfully() {
        //given
        var input = new CreateTaskInput("Learn java", Optional.of("study spring with hibernate"));

        //when
        TaskOutput output = useCase.execute(input);

        //then
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals("Learn java", output.title());
        assertEquals(Optional.of("study spring with hibernate"), output.description());
    }
}