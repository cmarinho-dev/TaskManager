package br.com.cmarinho.taskmanager.application;

import br.com.cmarinho.taskmanager.application.input.CreateTaskInput;
import br.com.cmarinho.taskmanager.application.output.TaskOutput;
import br.com.cmarinho.taskmanager.domain.Task;
import br.com.cmarinho.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskUseCase {

    private final TaskRepository repository;

    public CreateTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    TaskOutput execute(CreateTaskInput input) {
        var task = new Task(input.title(), input.description());
        var saved = repository.save(task);
        return TaskOutput.from(saved);
    }
}
