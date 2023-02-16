package com.example.demo.config;

import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.eventhandling.scheduling.quartz.QuartzEventScheduler;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.serialization.Serializer;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Bean(initMethod = "start")
    public Scheduler scheduler() throws SchedulerException {
        return new StdSchedulerFactory().getScheduler();
    }

    @Bean(destroyMethod = "shutdown")
    public EventScheduler eventScheduler(Scheduler scheduler, EventBus eventBus, TransactionManager transactionManager, Serializer serializer) {
        return QuartzEventScheduler.builder().scheduler(scheduler).eventBus(eventBus).transactionManager(transactionManager).jobDataBinder(new QuartzEventScheduler.DirectEventJobDataBinder(serializer)).build();
    }

    @Bean
    public SnapshotTriggerDefinition snapshotTriggerDefinition(Snapshotter snapshotter) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, 20);
    }
}
