package br.com.cmarinho.taskmanager.application;

import br.com.cmarinho.taskmanager.application.input.UpdateTaskInput;
import br.com.cmarinho.taskmanager.application.output.TaskOutput;
import br.com.cmarinho.taskmanager.domain.TaskId;
import br.com.cmarinho.taskmanager.domain.TaskNotFoundException;
import br.com.cmarinho.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskUseCase {
    private final TaskRepository repository;

    public UpdateTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskOutput execute(TaskId id, UpdateTaskInput input) {
        var task = repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.update(input.title(), input.description(), input.status());

        var updated = repository.save(task);
        return TaskOutput.from(updated);
    }
}
