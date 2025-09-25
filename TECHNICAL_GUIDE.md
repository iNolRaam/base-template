# 🔧 TECHNICAL GUIDE - SPRING BOOT 4 ARCHITECTURE

## 🏗️ ARQUITECTURA ACTUALIZADA

### STACK TECNOLÓGICO FINAL
```yaml
Core Framework:
  Spring Boot: 4.0.0-M1
  Spring Framework: 7.0.0-M7 (auto-upgrade)
  Java: OpenJDK 21 (LTS)

Persistence Layer:
  Hibernate ORM: 7.0.7.Final (auto-upgrade)
  PostgreSQL Driver: 42.7.4
  HikariCP: Optimized connection pooling

Web Layer:
  Apache Tomcat: 11.0.9 (auto-upgrade)
  SpringDoc OpenAPI: 3.0.0-M1
  JSON Processing: Jackson (Spring Boot managed)

Performance & Caching:
  Caffeine Cache: 3.1.8
  Micrometer Metrics: 1.13.5
  Async Processing: Custom thread pools

Testing:
  JUnit: 5.x (Spring Boot managed)
  Testcontainers: 1.20.2
  Mockito: Latest (Spring Boot managed)

Build & Deployment:
  Maven: 3.9.x+
  Lombok: 1.18.32
```

## 🎯 HEXAGONAL ARCHITECTURE

### ESTRUCTURA DE DIRECTORIOS
```
src/main/java/com/inolraam/basetemplate/
├── adapter/                 # Adapters (Infrastructure)
│   ├── in/                  # Inbound adapters (Controllers)
│   │   └── web/            # REST controllers
│   │       ├── ProfileController.java
│   │       ├── RoleController.java
│   │       └── ...
│   └── out/                # Outbound adapters (Repositories)
│       └── jpa/           # JPA implementations
│           ├── entity/    # JPA entities
│           │   ├── ProfileEntity.java
│           │   ├── AuditableEntity.java (LocalDateTime)
│           │   └── ...
│           └── repository/ # JPA repositories
│               ├── ProfileJpaRepository.java
│               └── ...
├── config/                 # Configuration classes
│   ├── JpaAuditingConfig.java
│   ├── SwaggerConfig.java
│   ├── CacheConfig.java (NEW)
│   └── PerformanceConfig.java (NEW)
├── domain/                # Domain model (Core business logic)
│   ├── Profile.java
│   ├── Role.java
│   └── ...
├── usecase/               # Use cases (Application services)
│   ├── profile/
│   │   ├── ProfileService.java
│   │   └── ProfileCacheService.java (NEW)
│   └── ...
└── common/               # Common utilities
    ├── exception/
    └── util/
```

## 🚀 PERFORMANCE OPTIMIZATIONS

### 1. HIKARICP CONNECTION POOL
```yaml
# Configuración optimizada para production
spring:
  datasource:
    hikari:
      pool-name: SpringBoot4HikariCP
      minimum-idle: 5          # Connections in pool (idle)
      maximum-pool-size: 20    # Max total connections
      idle-timeout: 300000     # 5 minutes
      max-lifetime: 1200000    # 20 minutes  
      connection-timeout: 30000 # 30 seconds
      validation-timeout: 5000  # 5 seconds
      leak-detection-threshold: 60000 # 1 minute
      connection-test-query: SELECT 1
```

**Beneficios:**
- Detección automática de connection leaks
- Pool size optimizado para carga moderada-alta
- Timeouts configurados para evitar hanging connections
- Validación de conexiones antes de uso

### 2. HIBERNATE 7 OPTIMIZATIONS
```yaml
spring:
  jpa:
    properties:
      hibernate:
        # Batch Processing
        "[batch_fetch_style]": PADDED
        "[default_batch_fetch_size]": 16
        jdbc:
          "[batch_size]": 25
          "[fetch_size]": 50
          "[batch_versioned_data]": true
        
        # SQL Optimization
        "[order_inserts]": true
        "[order_updates]": true
        "[generate_statistics]": false
        
        # Connection Management
        connection:
          "[provider_disables_autocommit]": true
```

**Beneficios:**
- Batch operations: Reduce DB roundtrips
- Fetch optimization: Control memory usage
- SQL ordering: Better DB performance
- Connection handling: Improved transaction management

### 3. CAFFEINE CACHE SYSTEM
```java
@Configuration
@EnableCaching
public class CacheConfig {
    
    @Bean
    @Primary
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
            .maximumSize(1000)           # Max entries per cache
            .expireAfterAccess(10, TimeUnit.MINUTES)  # TTL
            .expireAfterWrite(30, TimeUnit.MINUTES)   # Max age
            .recordStats()               # Enable metrics
            .weakKeys()                  # Memory optimization
            .removalListener((key, value, cause) -> {
                // Custom removal handling
            })
        );
        
        return cacheManager;
    }
}
```

**Características:**
- **TTL**: 10 minutos después del último acceso
- **Max Age**: 30 minutos máximo en cache
- **Size Limit**: 1000 entries por cache
- **Memory Optimization**: Weak keys para GC
- **Metrics**: Estadísticas para monitoreo

### 4. ASYNC PROCESSING
```java
@Configuration
@EnableAsync
public class PerformanceConfig {
    
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("SpringBoot4-Async-");
        executor.setRejectedExecutionHandler(
            new ThreadPoolExecutor.CallerRunsPolicy()
        );
        return executor;
    }
    
    @Bean(name = "dbTaskExecutor")  
    public Executor dbTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("SpringBoot4-DB-");
        return executor;
    }
}
```

**Thread Pool Strategy:**
- **Main Pool**: 5-20 threads para operaciones generales
- **DB Pool**: 3-10 threads para operaciones de BD
- **Queue**: Buffer para picos de carga
- **Rejection Policy**: CallerRunsPolicy para backpressure

## 🗄️ DATABASE LAYER

### AUDIT SYSTEM MODERNIZADO
```java
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity {
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;  // Modernizado desde Date
    
    @LastModifiedDate  
    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdatedAt;  // Modernizado desde Date
    
    // Getters y setters
}
```

**Beneficios de LocalDateTime:**
- **Precision**: Nanosegundos vs millisegundos
- **Thread Safety**: Immutable objects
- **API Moderna**: Java 8+ Time API
- **No Deprecated**: Eliminación de @Temporal warnings
- **Database Compatibility**: TIMESTAMP mapping automático

### JPA REPOSITORIES OPTIMIZADOS
```java
@Repository
public interface ProfileJpaRepository extends JpaRepository<ProfileEntity, Long> {
    
    // Query methods optimizados
    @Query("SELECT p FROM ProfileEntity p WHERE p.isActive = true")
    List<ProfileEntity> findActiveProfiles();
    
    @Query("SELECT p FROM ProfileEntity p WHERE p.email = :email AND p.isActive = true")
    Optional<ProfileEntity> findByEmailAndActive(@Param("email") String email);
    
    // Batch operations
    @Modifying
    @Query("UPDATE ProfileEntity p SET p.isActive = false WHERE p.lastUpdatedAt < :date")
    void deactivateOldProfiles(@Param("date") LocalDateTime cutoffDate);
}
```

## 🌐 WEB LAYER

### SPRING BOOT 4 CONTROLLERS
```java
@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {
    
    private final ProfileService profileService;
    private final ProfileCacheService cacheService;  // New cached service
    
    @GetMapping("/{id}")
    public ResponseEntity<ProfileEntity> getProfile(@PathVariable Long id) {
        // Usa cache service para mejor performance
        return cacheService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ProfileEntity> createProfile(@RequestBody ProfileEntity profile) {
        ProfileEntity saved = cacheService.save(profile);  // Cache eviction automático
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
```

### OPENAPI 3 DOCUMENTATION
```java
@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Base Template API")
                .version("4.0.0")
                .description("Spring Boot 4 REST API with Hibernate 7"))
            .servers(Arrays.asList(
                new Server().url("http://localhost:8080").description("Development"),
                new Server().url("https://api.example.com").description("Production")
            ));
    }
}
```

## 📊 OBSERVABILITY & MONITORING

### ACTUATOR ENDPOINTS
```yaml
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,env,configprops,loggers
  endpoint:
    health:
      show-details: when-authorized
      show-components: always
  health:
    db:
      enabled: true
    diskspace:
      enabled: true
```

**Available Endpoints:**
- `/actuator/health`: Application health status
- `/actuator/metrics`: Micrometer metrics
- `/actuator/info`: Application information  
- `/actuator/env`: Environment properties
- `/actuator/configprops`: Configuration properties

### MICROMETER METRICS
```yaml
management:
  metrics:
    enable:
      hikaricp: true    # Connection pool metrics
      jvm: true         # JVM memory, GC, threads
      jdbc: true        # Database metrics
      system: true      # System CPU, disk, network
  tracing:
    sampling:
      probability: 1.0  # 100% in dev, 0.1 in prod
```

**Key Metrics Available:**
```bash
# HikariCP Metrics
hikaricp.connections.active
hikaricp.connections.idle
hikaricp.connections.pending
hikaricp.connections.usage

# JVM Metrics  
jvm.memory.used
jvm.memory.max
jvm.gc.pause
jvm.threads.live

# System Metrics
system.cpu.usage
system.disk.free
system.load.average.1m

# Custom Cache Metrics
cache.gets
cache.puts
cache.evictions
cache.size
```

## 🧪 TESTING STRATEGY

### TEST CONFIGURATION
```java
@DataJpaTest
@Testcontainers
class ProfileJpaRepositoryTest {
    
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired  
    private ProfileJpaRepository repository;
    
    @Test
    void shouldFindProfileById() {
        // Given
        ProfileEntity profile = new ProfileEntity();
        profile.setEmail("test@example.com");
        profile.setCreatedAt(LocalDateTime.now());  // Modern API
        entityManager.persistAndFlush(profile);
        
        // When
        Optional<ProfileEntity> found = repository.findById(profile.getId());
        
        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("test@example.com");
    }
}
```

### CACHE TESTING
```java
@SpringBootTest
@TestPropertySource(properties = {
    "spring.cache.type=caffeine",
    "logging.level.org.springframework.cache=DEBUG"
})
class ProfileCacheServiceTest {
    
    @Autowired
    private ProfileCacheService cacheService;
    
    @MockBean
    private ProfileJpaRepository repository;
    
    @Test
    void shouldCacheProfileLookup() {
        // Given
        ProfileEntity profile = createTestProfile();
        when(repository.findById(1L)).thenReturn(Optional.of(profile));
        
        // When - First call
        Optional<ProfileEntity> first = cacheService.findById(1L);
        Optional<ProfileEntity> second = cacheService.findById(1L);
        
        // Then
        verify(repository, times(1)).findById(1L);  // Only called once due to cache
        assertThat(first).isEqualTo(second);
    }
}
```

## 🔧 CONFIGURATION MANAGEMENT

### PROFILES CONFIGURATION
```yaml
# application.yml (base)
spring:
  profiles:
    active: ${SPRING_PROFILES_ACTIVE:dev}

---
# Development Profile
spring:
  config:
    activate:
      on-profile: dev
  jpa:
    show-sql: true
    properties:
      hibernate:
        "[format_sql]": true

---  
# Production Profile
spring:
  config:
    activate:
      on-profile: prod
  jpa:
    show-sql: false
    properties:
      hibernate:
        "[format_sql]": false
        "[generate_statistics]": true  # For monitoring
```

### EXTERNALIZED CONFIGURATION
```bash
# Environment Variables
export SPRING_PROFILES_ACTIVE=prod
export DB_HOST=prod-db-server
export DB_USERNAME=app_user
export DB_PASSWORD=secure_password
export CACHE_TTL_MINUTES=15
export THREAD_POOL_SIZE=30

# Application startup
java -jar basetemplate.war \
  --spring.profiles.active=prod \
  --spring.datasource.url=jdbc:postgresql://${DB_HOST}:5432/db_basetemplate \
  --spring.datasource.username=${DB_USERNAME} \
  --spring.datasource.password=${DB_PASSWORD}
```

## 🚨 ERROR HANDLING

### GLOBAL EXCEPTION HANDLER
```java
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        log.warn("Entity not found: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse("ENTITY_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        log.error("Data integrity violation: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse("DATA_INTEGRITY_ERROR", "Data constraint violation");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        log.error("Unexpected error: ", ex);
        ErrorResponse error = new ErrorResponse("INTERNAL_ERROR", "An unexpected error occurred");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
```

## 📈 PERFORMANCE BENCHMARKS

### EXPECTED METRICS
```yaml
Startup Time:
  Development: < 6 seconds
  Production: < 5 seconds
  
Memory Usage:
  Development: < 500MB heap
  Production: < 1GB heap
  
Response Times:
  Simple queries: < 50ms
  Complex queries: < 200ms
  Cached responses: < 10ms
  
Throughput:
  Development: > 500 req/s
  Production: > 2000 req/s
  
Database Connections:
  Pool min: 5 connections
  Pool max: 20 connections
  Typical usage: 8-12 connections
```

---
**🔧 TECHNICAL IMPLEMENTATION COMPLETE**  
**Architecture**: Hexagonal + Spring Boot 4 + Optimized Performance**  
**Stack**: Production-ready with monitoring, caching, and modern APIs**