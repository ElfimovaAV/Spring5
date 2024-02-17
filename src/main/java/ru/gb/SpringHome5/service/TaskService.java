package ru.gb.SpringHome5.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.SpringHome5.model.Task;
import ru.gb.SpringHome5.model.TaskStatus;
import ru.gb.SpringHome5.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    public List<Task> getAllTasks(){
        return repository.findAll();
    }
    public Task createTask(Task task){
        return repository.save(task);
    }

    public List<Task> getTasksByStatus(TaskStatus status){
        return repository.findByStatus(status);
    }
    public Task updateTaskStatus(Long id, Task taskDetails){
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setDescription(taskDetails.getDescription());
            task.setStatus(taskDetails.getStatus());
            task.setCreateTime(taskDetails.getCreateTime());
            return repository.save(task);
        } else {
            throw new IllegalArgumentException("Task not found with id: " + id);
        }
    }

    public void deleteTask(Long id){
        repository.deleteById(id);
    }

}
