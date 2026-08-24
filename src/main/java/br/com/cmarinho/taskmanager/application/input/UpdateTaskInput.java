package br.com.cmarinho.taskmanager.application.input;

import br.com.cmarinho.taskmanager.domain.TaskStatus;

import java.util.Optional;

public record UpdateTaskInput(Optional<String> title,
                              Optional<String> description,
                              Optional<TaskStatus> status) {
}
