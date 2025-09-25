# 🚀 DEPLOYMENT GUIDE - SPRING BOOT 4 PRODUCTION

## 📋 REQUISITOS DEL SISTEMA

### ☕ JAVA REQUIREMENTS
```bash
# Java Version
OpenJDK 21 (LTS)
# Verificación
java -version
# Expected: openjdk version "21.x.x"
```

### 🗄️ DATABASE REQUIREMENTS
```yaml
PostgreSQL: >= 12.x (Recomendado 15.x+)
Driver: postgresql-42.7.4.jar (incluido)
Schema: base_template
Encoding: UTF-8
```

### 💾 MEMORY REQUIREMENTS
```yaml
Desarrollo: 
  Min Heap: 512MB (-Xms512m)
  Max Heap: 1GB (-Xmx1g)

Producción:
  Min Heap: 1GB (-Xms1g)
  Max Heap: 2GB (-Xmx2g)
  
Recommended: 4GB total RAM
```

### 🌐 NETWORK REQUIREMENTS
```yaml
Ports:
  Application: 8080 (configurable)
  Actuator: 8080/actuator/* 
  Database: 5432 (PostgreSQL)
  
Firewall:
  Inbound: 8080 (HTTP)
  Outbound: 5432 (DB), 443 (HTTPS)
```

## 🏗️ DEPLOYMENT STEPS

### PASO 1: PREPARACIÓN DEL ENTORNO
```bash
# 1. Verificar Java 21
java -version

# 2. Crear directorio de aplicación
mkdir -p /opt/basetemplate
cd /opt/basetemplate

# 3. Crear usuario de aplicación (opcional)
sudo useradd -r -s /bin/false basetemplate
sudo chown -R basetemplate:basetemplate /opt/basetemplate
```

### PASO 2: DATABASE SETUP
```sql
-- 1. Crear base de datos
CREATE DATABASE db_basetemplate 
  WITH ENCODING='UTF8' 
  LC_COLLATE='en_US.UTF-8' 
  LC_CTYPE='en_US.UTF-8';

-- 2. Crear usuario
CREATE USER postgres WITH PASSWORD 'your_secure_password';
GRANT ALL PRIVILEGES ON DATABASE db_basetemplate TO postgres;

-- 3. Crear schema
\c db_basetemplate
CREATE SCHEMA base_template;
GRANT ALL ON SCHEMA base_template TO postgres;
```

### PASO 3: APPLICATION DEPLOYMENT
```bash
# 1. Build de la aplicación
mvn clean package -DskipTests

# 2. Copiar WAR al servidor
scp target/base-template-0.0.1-SNAPSHOT.war server:/opt/basetemplate/

# 3. Renombrar para simplicidad
mv base-template-0.0.1-SNAPSHOT.war basetemplate.war
```

### PASO 4: CONFIGURACIÓN DE PRODUCCIÓN

#### application-prod.yml
```yaml
spring:
  profiles:
    active: prod
  application:
    name: base-template-prod
  
  # Database Production Config
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/db_basetemplate
    username: ${DB_USERNAME:postgres}
    password: ${DB_PASSWORD:your_password}
    
    # HikariCP Production Optimized
    hikari:
      minimum-idle: 10
      maximum-pool-size: 50
      idle-timeout: 600000
      pool-name: BaseTemplate-Prod-HikariCP
      max-lifetime: 1800000
      connection-timeout: 30000
      validation-timeout: 5000
      leak-detection-threshold: 120000
      connection-test-query: SELECT 1

  # JPA Production Config  
  jpa:
    properties:
      hibernate:
        "[default_schema]": base_template
        "[format_sql]": false
        "[show_sql]": false
        "[batch_fetch_style]": PADDED
        "[default_batch_fetch_size]": 32
        "[enable_lazy_load_no_trans]": false
        jdbc:
          "[batch_size]": 50
          "[fetch_size]": 100
          "[batch_versioned_data]": true
        "[order_inserts]": true
        "[order_updates]": true
        "[generate_statistics]": false
        connection:
          "[provider_disables_autocommit]": true
    show-sql: false
    open-in-view: false
    hibernate:
      ddl-auto: validate

# Production Actuator Config
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
      base-path: /actuator
  endpoint:
    health:
      show-details: when-authorized
      show-components: always
  health:
    db:
      enabled: true
    diskspace:
      enabled: true
  info:
    env:
      enabled: true
    java:
      enabled: true
    os:
      enabled: true
  
  # Production Metrics
  metrics:
    enable:
      hikaricp: true
      jvm: true
      jdbc: true
      system: true
  simple:
    metrics:
      export:
        enabled: true
  tracing:
    sampling:
      probability: 0.1  # 10% sampling in production

# Logging Production Config
logging:
  level:
    root: INFO
    com.inolraam.basetemplate: INFO
    org.springframework.web: WARN
    org.hibernate: WARN
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: /var/log/basetemplate/application.log
    max-size: 100MB
    max-history: 30

# Server Production Config
server:
  port: 8080
  servlet:
    context-path: /
  tomcat:
    max-threads: 200
    min-spare-threads: 10
    max-connections: 8192
    connection-timeout: 20000
  compression:
    enabled: true
    mime-types: text/html,text/xml,text/plain,text/css,text/javascript,application/javascript,application/json
  error:
    whitelabel:
      enabled: false
```

### PASO 5: SYSTEMD SERVICE (Linux)
```bash
# Crear archivo de servicio
sudo nano /etc/systemd/system/basetemplate.service
```

```ini
[Unit]
Description=Base Template Spring Boot 4 Application
After=network.target

[Service]
Type=forking
User=basetemplate
Group=basetemplate
WorkingDirectory=/opt/basetemplate

# Java Options para Producción
Environment="JAVA_OPTS=-Xms1g -Xmx2g -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:+UnlockExperimentalVMOptions -XX:+UseZGC"
Environment="SPRING_PROFILES_ACTIVE=prod"
Environment="DB_USERNAME=postgres"
Environment="DB_PASSWORD=your_secure_password"

ExecStart=/usr/bin/java $JAVA_OPTS -jar /opt/basetemplate/basetemplate.war
ExecStop=/bin/kill -TERM $MAINPID

Restart=always
RestartSec=10
StandardOutput=journal
StandardError=journal
SyslogIdentifier=basetemplate

[Install]
WantedBy=multi-user.target
```

```bash
# Habilitar y iniciar servicio
sudo systemctl daemon-reload
sudo systemctl enable basetemplate
sudo systemctl start basetemplate

# Verificar estado
sudo systemctl status basetemplate
```

## 📊 MONITOREO Y OBSERVABILIDAD

### HEALTH CHECKS
```bash
# Basic Health Check
curl http://localhost:8080/actuator/health

# Detailed Health (if authorized)
curl -H "Authorization: Bearer token" http://localhost:8080/actuator/health

# Expected Response
{
  "status": "UP",
  "components": {
    "db": {"status": "UP"},
    "diskSpace": {"status": "UP"}
  }
}
```

### METRICS ENDPOINTS
```bash
# JVM Metrics
curl http://localhost:8080/actuator/metrics/jvm.memory.used

# HikariCP Metrics
curl http://localhost:8080/actuator/metrics/hikaricp.connections.active

# System Metrics
curl http://localhost:8080/actuator/metrics/system.cpu.usage

# Custom App Metrics
curl http://localhost:8080/actuator/metrics
```

### LOG MONITORING
```bash
# Application logs
tail -f /var/log/basetemplate/application.log

# System logs
journalctl -u basetemplate -f

# Error grep
tail -f /var/log/basetemplate/application.log | grep ERROR
```

## 🔒 SECURITY CONSIDERATIONS

### PRODUCTION SECURITY
```yaml
# application-prod.yml security additions
spring:
  security:
    # Disable default security for actuator in prod
    user:
      name: admin
      password: ${ACTUATOR_PASSWORD:secure_password}

management:
  endpoints:
    web:
      exposure:
        # Limitar endpoints en producción
        include: health,info,metrics
  security:
    enabled: true
```

### FIREWALL CONFIGURATION
```bash
# UFW (Ubuntu Firewall)
sudo ufw allow 8080/tcp
sudo ufw allow from trusted_ip to any port 8080
sudo ufw enable

# iptables example
sudo iptables -A INPUT -p tcp --dport 8080 -j ACCEPT
sudo iptables -A INPUT -p tcp --dport 5432 -s localhost -j ACCEPT
```

### SSL/TLS CONFIGURATION
```yaml
# HTTPS Configuration (optional)
server:
  ssl:
    enabled: true
    key-store: /path/to/keystore.p12
    key-store-password: ${SSL_PASSWORD}
    key-store-type: PKCS12
  port: 8443
```

## 🚨 TROUBLESHOOTING

### COMMON ISSUES

#### Startup Issues
```bash
# Check Java version
java -version

# Check memory
free -h

# Check port availability
netstat -tlnp | grep 8080

# Check logs
journalctl -u basetemplate --since "1 hour ago"
```

#### Database Connection Issues
```bash
# Test PostgreSQL connection
psql -h localhost -U postgres -d db_basetemplate -c "\l"

# Check HikariCP pool
curl http://localhost:8080/actuator/metrics/hikaricp.connections

# Validate schema
psql -h localhost -U postgres -d db_basetemplate -c "\dn"
```

#### Performance Issues
```bash
# JVM metrics
curl http://localhost:8080/actuator/metrics/jvm.memory.used
curl http://localhost:8080/actuator/metrics/jvm.gc.pause

# Thread pool status
curl http://localhost:8080/actuator/metrics/executor.active

# Cache metrics
curl http://localhost:8080/actuator/metrics | grep cache
```

### PERFORMANCE TUNING

#### JVM Tuning
```bash
# G1GC (Recommended for Spring Boot 4)
-XX:+UseG1GC
-XX:MaxGCPauseMillis=200
-XX:G1HeapRegionSize=16m

# ZGC (Java 21 feature)
-XX:+UnlockExperimentalVMOptions
-XX:+UseZGC
-XX:+UnlockDiagnosticVMOptions

# Memory settings
-Xms1g -Xmx2g
-XX:MetaspaceSize=256m
-XX:MaxMetaspaceSize=512m
```

#### Application Tuning
```yaml
# Thread pool optimization
spring:
  task:
    execution:
      pool:
        core-size: 8
        max-size: 20
        queue-capacity: 100

# Tomcat optimization  
server:
  tomcat:
    threads:
      max: 200
      min-spare: 20
    connection-timeout: 20000
    max-connections: 8192
```

## 📈 SCALING CONSIDERATIONS

### HORIZONTAL SCALING
- Load balancer configuration
- Session management (stateless)
- Database connection pooling
- Cache coordination (if multiple instances)

### VERTICAL SCALING
- Memory: 4GB+ recommended
- CPU: 4+ cores for production
- Storage: SSD recommended for logs and temp files

---
**🚀 DEPLOYMENT READY**  
**Stack**: Spring Boot 4.0.0-M1 + Production Optimized**  
**Environment**: Production-ready with monitoring and security**