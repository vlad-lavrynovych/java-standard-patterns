package com.demo.patterns.enterprise.queue.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TaskProcessingRoute extends RouteBuilder {
    @Override
    public void configure() {
        from("jpa:com.demo.patterns.enterprise.entity.Task?consumer.delay=5000&consumer.query=SELECT t FROM Task t WHERE t.status='QUEUED'")
            .log("Processing task: ${body}")
            .bean("taskProcessor", "processTask")
            .to("direct:notifyB2B");

        from("direct:notifyB2B")
            .log("Notifying B2B clients about task completion.");
    }
}
