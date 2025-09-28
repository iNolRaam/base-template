package com.inolraam.basetemplate.config.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DBInitializationChecker {

    @EventListener(ApplicationReadyEvent.class)
    @Order(1)
    public void onApplicationReady(ApplicationReadyEvent event) {
        String activeProfile = getActiveProfile(event);
        
        if ("joindb".equals(activeProfile)) {
            this.logInitializationSuccess();
            this.signalCompletionToMaven();
        }
    }

    private String getActiveProfile(ApplicationReadyEvent event) {
        return event.getApplicationContext()
                .getEnvironment()
                .getActiveProfiles()[0];
    }

    private void logInitializationSuccess() {
        log.info("=".repeat(50));
        log.info("DATABASE INITIALIZATION COMPLETED SUCCESSFULLY");
        log.info("Schema files have been processed and applied");
        log.info("Application ready for shutdown");
        log.info("=".repeat(50));
    }

    private void signalCompletionToMaven() {
        System.setProperty("database.initialization.completed", "true");
    }
}