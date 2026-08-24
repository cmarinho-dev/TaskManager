package br.com.cmarinho.taskmanager.application;

import br.com.cmarinho.taskmanager.application.output.TaskOutput;
import br.com.cmarinho.taskmanager.domain.TaskId;
import br.com.cmarinho.taskmanager.domain.TaskNotFoundException;
import br.com.cmarinho.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteTaskUseCase {
    private final TaskRepository repository;

    public DeleteTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public void execute(TaskId id) {
        if (repository.findById(id).isEmpty()) {
            throw new TaskNotFoundException(id);
        }
        repository.delete(id);
    }
}
