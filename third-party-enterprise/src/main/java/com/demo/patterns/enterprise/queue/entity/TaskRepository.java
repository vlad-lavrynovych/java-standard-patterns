package com.demo.patterns.enterprise.queue.entity;

import com.demo.patterns.enterprise.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
