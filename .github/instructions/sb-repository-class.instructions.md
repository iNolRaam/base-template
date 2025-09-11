# Sets the standards for entity class design including annotations, ID generation strategies, and relationship configurations for database interaction.
applyTo: '**/src/main/java/com/inolraam/basetemplate/adapter/out/jpa/repository/*.java'
---
#Standards for Entity Classes in Spring Boot Applications

When creating repository classes for adapter layout "out" in a Spring Boot application, adhere to the following standards to ensure consistency, maintainability, and best practices:
- Must extend JpaRepository<EntityClass, Long>.
- The EntityClass must be the name of the entity class that the repository is for.