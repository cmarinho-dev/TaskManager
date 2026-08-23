package br.com.cmarinho.taskmanager.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

abstract class TaskRepositoryTest {

    TaskRepository repository;

    protected abstract TaskRepository createRepository();

    @BeforeEach
    void setUp() {
        repository = createRepository();
    }

    @Test
    void should_save_and_retrieve_task_by_id() {
        //given
        var task = new Task("Buy a coffee", Optional.empty());

        //when
        var saved = repository.save(task);
        Optional<Task> result = repository.findById(saved.getId());

        //then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(task.getId());
        assertThat(result.get().getTitle()).isEqualTo(task.getTitle());
        assertThat(result.get().getDescription()).isEqualTo(task.getDescription());
        assertThat(result.get().getStatus()).isEqualTo(task.getStatus());
    }

    @Test
    void should_find_all_persisted_tasks() {
        //given
        var task1 = new Task("go to the shopping", Optional.of("Lorem ipsum abc cde"));
        var task2 = new Task("visit america park", Optional.of("I need to take pictures at snow"));

        repository.save(task1);
        repository.save(task2);

        //when
        List<Task> tasks = repository.findAll();

        //then
        assertThat(tasks).hasSize(2);
        assertThat(tasks).extracting(Task::getId).containsExactlyInAnyOrder(task1.getId(), task2.getId());

    }

    @Test
    void should_delete_tasks_by_id() {
        //given
        var task = new Task("go to the gym", Optional.empty());
        var taskId = task.getId();

        //when
        repository.delete(taskId);
        Optional<Task> result = repository.findById(taskId);

        //then
        assertThat(result).isEmpty();

    }

    @Test
    void should_return_empty_when_searching_non_existent_tasks() {
        //given
        var nonExistentId = new TaskId();

        //when
        Optional<Task> result = repository.findById(nonExistentId);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    void should_update_task_status_successfully() {
        //given
        var task = new Task("buy gta", Optional.empty());
        repository.save(task);

        task.setDescription(Optional.of("buy and download gta vi"));
        task.setStatus(TaskStatus.COMPLETED);

        //when
        repository.save(task);
        Optional<Task> result = repository.findById(task.getId());

        //then
        assertThat(result).isPresent();
        assertThat(result.get().getDescription()).isEqualTo(Optional.of("buy and download gta vi"));
        assertThat(result.get().getStatus()).isEqualTo(TaskStatus.COMPLETED);
    }
}