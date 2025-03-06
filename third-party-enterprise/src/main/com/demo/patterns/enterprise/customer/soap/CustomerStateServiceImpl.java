package com.demo.patterns.enterprise.customer.soap;

import com.demo.patterns.enterprise.queue.entity.Task;
import com.demo.patterns.enterprise.queue.entity.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerStateServiceImpl implements CustomerStateService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public String changeCustomerState(Long customerId, String newState) {
        Task task = new Task();
//        task.setStatus("QUEUED");
        taskRepository.save(task);
        return "Task queued to change state of customer ID " + customerId;
    }
}
