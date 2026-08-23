package br.com.cmarinho.taskmanager.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.Optional;

@Data
public class Task {
    private final TaskId id;
    private String title;
    private Optional<String> description;
    private TaskStatus status;

    public Task(String title, Optional<String> description) {
        Assert.notNull(title, "title must not be null");

        this.id = new TaskId();
        this.status = TaskStatus.PENDING;
        this.title = title;
        this.description = description;
    }
}