---
# Sets the standards for entity class design including annotations, ID generation strategies, and relationship configurations for database interaction.
applyTo: '**/src/main/java/com/inolraam/basetemplate/adapter/out/jpa/entity/*.java'
---
#Standards for Entity Classes in Spring Boot Applications

When creating entity classes in a Spring Boot application, adhere to the following standards to ensure consistency, maintainability, and best practices:
- Must annotate entity classes with @Entity.
- Must specify the table name using @Table(name="table_name").
- Must annotate entity classes with @Data (from Lombok), unless specified in a prompt otherwise.
- Must annotate entity classes with @NoArgsConstructor (from Lombok), unless specified in a prompt otherwise.
- Must annotate entity ID with @Id, @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_name") and @SequenceGenerator(name = "sequence_name", allocationSize = 1).
- Must use FetchType.LAZY for relationships, unless specified in a prompt otherwise.
- If specifying that the entity class is a catalog must extend BaseCatalogEntity.