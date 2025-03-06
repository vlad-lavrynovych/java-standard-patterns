package com.demo.patterns.enterprise.queue.api;

import com.demo.patterns.enterprise.queue.entity.Task;

public interface TaskHandler {
    boolean canHandle(Task task);
    void handle(Task task);
}
