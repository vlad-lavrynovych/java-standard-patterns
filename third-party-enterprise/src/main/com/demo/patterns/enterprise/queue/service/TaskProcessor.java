package com.demo.patterns.enterprise.queue.service;

import com.demo.patterns.enterprise.queue.api.TaskHandler;
import com.demo.patterns.enterprise.queue.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskProcessor {

    private final List<TaskHandler> taskHandlers;

    public void processTask(Task task) {
        taskHandlers.stream()
            .filter(handler -> handler.canHandle(task))
            .forEach(handler -> handler.handle(task));
    }
}
