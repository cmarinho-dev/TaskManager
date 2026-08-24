package br.com.cmarinho.taskmanager.application;

import br.com.cmarinho.taskmanager.application.output.TaskOutput;
import br.com.cmarinho.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTasksUseCase {
    private final TaskRepository repository;

    public GetTasksUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public List<TaskOutput> execute() {
        return repository.findAll().stream().map(TaskOutput::from)
                .toList();
    }
}
