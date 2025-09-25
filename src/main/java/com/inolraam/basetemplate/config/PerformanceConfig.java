package com.inolraam.basetemplate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Configuración de rendimiento optimizada para Spring Boot 4.
 * 
 * Características:
 * - Thread pool optimizado para operaciones asíncronas
 * - Configuración específica para alta concurrencia
 * - Preparado para Virtual Threads (Project Loom) de Java 21
 * 
 * @author BaseTemplate
 * @version 4.0.0
 * @since Spring Boot 4.0.0-M1
 */
@Configuration
@EnableAsync
public class PerformanceConfig {

    /**
     * Configuración del Thread Pool para operaciones asíncronas.
     * Optimizado para Spring Boot 4 y Java 21.
     * 
     * @return Executor configurado para máximo rendimiento
     */
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        
        // Configuración optimizada para aplicaciones modernas
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("SpringBoot4-Async-");
        
        // Política de rechazo optimizada
        executor.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
        
        // Importante: inicializar el executor
        executor.initialize();
        
        return executor;
    }

    /**
     * Executor específico para operaciones de base de datos.
     * Separado del pool principal para mejor gestión de recursos.
     * 
     * @return Executor para operaciones DB
     */
    @Bean(name = "dbTaskExecutor")
    public Executor dbTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(50);
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix("SpringBoot4-DB-");
        
        executor.initialize();
        
        return executor;
    }
}