package com.ets.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ets.model.Task;
import com.ets.statusenum.TaskStatus;
import com.ets.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository repository;

    // ✅ Constructor Injection (Best Practice)
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    // ✅ Create Task
    public Task createTask(Task task) {
        task.setStatus(TaskStatus.PENDING);
        return repository.save(task);
    }


    // ✅ Get All Tasks
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    // ✅ Get Task By ID
    public Task getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    // ✅ Update Task
    public Task updateTask(Long id, Task updated) {

        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        task.setDate(updated.getDate());
        task.setTime(updated.getTime());
        task.setTaskDescription(updated.getTaskDescription());
        task.setChallengesFaced(updated.getChallengesFaced());
        task.setUploadScreenshots(updated.getUploadScreenshots());
        task.setSolutionImplemented(updated.getSolutionImplemented());
        task.setUploadSolutionDocuments(updated.getUploadSolutionDocuments());
        task.setStatus(updated.getStatus());

        return repository.save(task);
    }

    // ✅ Delete Task
    public void deleteTask(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        repository.deleteById(id);
    }
}

