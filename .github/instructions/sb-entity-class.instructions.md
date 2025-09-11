---
# Sets the standards for entity class design including annotations, ID generation strategies, and relationship configurations for database interaction.
applyTo: '**/src/main/java/com/inolraam/basetemplate/adapter/out/jpa/entity/*.java'
---
#Standards for Entity Classes in Spring Boot Applications

When creating entity classes in a Spring Boot application, adhere to the following standards to ensure consistency, maintainability, and best practices:
- Must annotate entity classes with @Entity.
- Must specify the table name using @Table(name="table_name").
- The table name into the @Table annotation must be in snake_case format and plural.
- Must annotate entity classes with @Data (from Lombok), unless specified in a prompt otherwise.
- Must annotate entity classes with @NoArgsConstructor (from Lombok), unless specified in a prompt otherwise.
- Must annotate entity ID with @Id, @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_name") and @SequenceGenerator(name = "sequence_name", allocationSize = 1).
- The sequence name into the @GeneratedValue and @SequenceGenerator annotations must be in snake_case format with this format "seq_(table_name)_id", ignore the parentheses.
- Must use FetchType.LAZY for relationships, unless specified in a prompt otherwise.
- If specifying that the entity class is a catalog must extend BaseCatalogEntity class on the other hand must extend AuditableEntity class.
- If you create a class that extends the other class you should not add properties that the parent already has.
- Create a SQL code for the new sequence and new table.
- You must follow the naming conventions for tables and sequences:
  - Table names must be in snake_case format and plural.
  - Sequence names must be in snake_case format with this format "seq_(table_name)_id", ignore the parentheses.
- For SQL code you must follow the rules for PostgreSQL 15.
- In SQL code for the new table you must include the audit fields (created_by, created_at, updated_by, lst_updated_date).
- Add the SQL code for sequence in 'src\main\resources\DDL\02_sequences.sql'
- Add the SQL code for table in 'src\main\resources\DDL\03_tables.sql'
- Create the new file for the entity class in the path '**/src/main/java/com/inolraam/basetemplate/adapter/out/jpa/entity/'
- Create the Repository interface for the new entity class in the path '**/src/main/java/com/inolraam/basetemplate/adapter/out/jpa/repository/'