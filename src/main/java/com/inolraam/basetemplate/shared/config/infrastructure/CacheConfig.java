package com.inolraam.basetemplate.shared.config.infrastructure;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Configuración de cache optimizada para Spring Boot 4 y alto rendimiento.
 * 
 * Características:
 * - Cache en memoria con Caffeine (alto rendimiento)
 * - Configuración optimizada para aplicaciones con alta concurrencia
 * - Métricas y estadísticas habilitadas
 * - Configuración de TTL y límites de memoria
 * 
 * @author BaseTemplate
 * @version 4.0.0
 * @since Spring Boot 4.0.0-M1
 */
@Configuration
@EnableCaching
public class CacheConfig {

    private static final String CACHE_NAMES = "profiles,roles,rights,typeRights";

    /**
     * Configuración del CacheManager con Caffeine para máximo rendimiento.
     * 
     * Optimizaciones:
     * - Maximum size: 1000 entries por cache
     * - Expire after access: 10 minutos de inactividad
     * - Record stats: Habilitado para monitoreo
     * - Weak keys: Optimización de memoria
     * 
     * @return CacheManager configurado con Caffeine
     */
    @Bean
    @Primary
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(defaultCaffeineConfig());
        
        // Pre-configure cache names for better performance
        cacheManager.setCacheNames(Arrays.asList(CACHE_NAMES.split(",")));
        
        return cacheManager;
    }

     /**
     * Default Caffeine cache configuration.
     * Applied to all caches unless overridden.
     */
    private Caffeine<Object, Object> defaultCaffeineConfig() {
        return Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .recordStats()
                .weakKeys()
                .removalListener((key, value, cause) -> {
                /* Cache entry removal handler - can be extended for monitoring */
            });
    }

    /**
     * Configuración adicional de cache específica para entities.
     * Útil para cachear resultados de consultas frecuentes.
     * 
     * @return CacheManager para entidades con configuración específica
     */
    @Bean("entityCacheManager")
    public CacheManager entityCacheManager() {
        CaffeineCacheManager entityCacheManager = new CaffeineCacheManager();
        entityCacheManager.setCaffeine(Caffeine.newBuilder()
            .maximumSize(500)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .recordStats()
        );
        
        return entityCacheManager;
    }
}