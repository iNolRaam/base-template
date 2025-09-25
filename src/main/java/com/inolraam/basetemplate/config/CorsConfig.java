package com.inolraam.basetemplate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * Configuración CORS para Spring Boot 4 - Soluciona NetworkError en Swagger UI.
 * 
 * Esta configuración permite que Swagger UI pueda realizar peticiones AJAX
 * al backend sin problemas de CORS (Cross-Origin Resource Sharing).
 * 
 * @author BaseTemplate
 * @version 4.0.0
 * @since Spring Boot 4.0.0-M1
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configuración global de CORS para todos los endpoints.
     * Permite requests desde Swagger UI y otros orígenes de desarrollo.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOriginPatterns("*")  // Permite cualquier origen en desarrollo
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);  // Cache preflight por 1 hora
        
        // Configuración específica para actuator endpoints
        registry.addMapping("/actuator/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    /**
     * Bean de configuración CORS más detallada.
     * Usado por Spring Security si está presente.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Orígenes permitidos (desarrollo)
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        
        // Métodos HTTP permitidos
        configuration.setAllowedMethods(Arrays.asList(
            "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
        ));
        
        // Headers permitidos
        configuration.setAllowedHeaders(Arrays.asList("*"));
        
        // Permitir credenciales
        configuration.setAllowCredentials(true);
        
        // Headers expuestos en la respuesta
        configuration.setExposedHeaders(Arrays.asList(
            "Authorization", "Cache-Control", "Content-Type"
        ));
        
        // Cache de preflight requests
        configuration.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}