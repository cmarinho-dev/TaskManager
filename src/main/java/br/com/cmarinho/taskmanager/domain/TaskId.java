package br.com.cmarinho.taskmanager.domain;

import org.springframework.util.Assert;

import java.util.UUID;

public record TaskId(UUID id) {
    public TaskId {
        Assert.notNull(id, "id must be not null");
    }

    public  TaskId() {
        this(UUID.randomUUID());
    }
}
