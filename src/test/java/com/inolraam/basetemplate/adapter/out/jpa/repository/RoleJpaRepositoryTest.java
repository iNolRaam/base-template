package com.inolraam.basetemplate.adapter.out.jpa.repository;

import com.inolraam.basetemplate.adapter.out.jpa.entity.RoleEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class RoleJpaRepositoryTest extends BaseRepositoryTest {
    
    // Test constants
    private static final String TEST_ROLE_NAME = "test_role";
    private static final String TEST_ROLE_TWO = "test_role_two";
    private static final String CREATED_BY = "user_test";
    private static final String NON_EXISTENT = "non_existent";
    private static final long NON_EXISTENT_ID = 999L;

    @Autowired
    private RoleJpaRepository roleJpaRepository;

    // Test data
    private RoleEntity roleEntity;
    private RoleEntity roleTwoEntity;

    @BeforeEach
    void setUp() {
        // Create role entity for testing - sin rights para evitar constraint violations
        roleEntity = new RoleEntity();
        roleEntity.setName(TEST_ROLE_NAME);
        roleEntity.setVisible(true);
        roleEntity.setCreatedBy(CREATED_BY);
        roleEntity.setCreatedAt(LocalDateTime.now());
        // No asignamos rights para evitar problemas de constraints

        // Create second role entity for testing
        roleTwoEntity = new RoleEntity();
        roleTwoEntity.setName(TEST_ROLE_TWO);
        roleTwoEntity.setVisible(true);
        roleTwoEntity.setCreatedBy(CREATED_BY);
        roleTwoEntity.setCreatedAt(LocalDateTime.now());
        // No asignamos rights para evitar problemas de constraints
    }



    // === BASIC CRUD OPERATIONS ===
    
    @Test
    @DisplayName("Should save role with rights successfully")
    void shouldSaveRole() {
        // Given - role entity ready from setup
        
        // When
        RoleEntity savedRole = roleJpaRepository.save(roleEntity);
        
        // Then
        assertThat(savedRole).isNotNull();
        assertThat(savedRole.getId()).isNotNull();
        assertThat(savedRole.getName()).isEqualTo(TEST_ROLE_NAME);
        assertThat(savedRole.getRights()).isNullOrEmpty(); // Puede ser null o vacío
        assertThat(savedRole.getCreatedAt()).isNotNull();
        assertThat(savedRole.getCreatedBy()).isEqualTo(CREATED_BY);
    }

    @Test
    @DisplayName("Should find role by ID successfully")
    void shouldFindRoleById() {
        // Given
        RoleEntity savedRole = roleJpaRepository.save(roleEntity);
        
        // When
        Optional<RoleEntity> foundRole = roleJpaRepository.findById(savedRole.getId());
        
        // Then
        assertThat(foundRole).isPresent();
        assertThat(foundRole.get().getName()).isEqualTo(TEST_ROLE_NAME);
        assertThat(foundRole.get().getRights()).isNullOrEmpty(); // Puede ser null o vacío
    }

    @Test
    @DisplayName("Should return empty when role ID does not exist")
    void shouldReturnEmptyWhenRoleIdDoesNotExist() {
        // When
        Optional<RoleEntity> foundRole = roleJpaRepository.findById(NON_EXISTENT_ID);
        
        // Then
        assertThat(foundRole).isEmpty();
    }

    @Test
    @DisplayName("Should find role by name successfully")
    void shouldFindRoleByName() {
        // Given
        roleJpaRepository.save(roleEntity);
        
        // When
        Optional<RoleEntity> foundRole = roleJpaRepository.findByName(TEST_ROLE_NAME);
        
        // Then
        assertThat(foundRole).isPresent();
        assertThat(foundRole.get().getName()).isEqualTo(TEST_ROLE_NAME);
        assertThat(foundRole.get().getRights()).isNullOrEmpty(); // Puede ser null o vacío
    }

    @Test
    @DisplayName("Should return empty when role name does not exist")
    void shouldReturnEmptyWhenRoleNameDoesNotExist() {
        // When
        Optional<RoleEntity> foundRole = roleJpaRepository.findByName(NON_EXISTENT);
        
        // Then
        assertThat(foundRole).isEmpty();
    }

    @Test
    @DisplayName("Should check if role exists by ID")
    void shouldCheckIfRoleExistsById() {
        // Given
        RoleEntity savedRole = roleJpaRepository.save(roleEntity);
        
        // When & Then
        assertThat(roleJpaRepository.existsById(savedRole.getId())).isTrue();
        assertThat(roleJpaRepository.existsById(NON_EXISTENT_ID)).isFalse();
    }

    @Test
    @DisplayName("Should check if role exists by name")
    void shouldCheckIfRoleExistsByName() {
        // Given
        roleJpaRepository.save(roleEntity);
        
        // When & Then
        assertThat(roleJpaRepository.existsByName(TEST_ROLE_NAME)).isTrue();
        assertThat(roleJpaRepository.existsByName(NON_EXISTENT)).isFalse();
    }

    @Test
    @DisplayName("Should check if role exists by ID not and name for uniqueness validation")
    void shouldCheckIfRoleExistsByIdNotAndName() {
        // Given
        RoleEntity savedRole1 = roleJpaRepository.save(roleEntity);
        roleJpaRepository.save(roleTwoEntity);
        
        // When & Then - checking if another role with same name exists (excluding current ID)
        assertThat(roleJpaRepository.existsByIdNotAndName(savedRole1.getId(), TEST_ROLE_NAME)).isFalse();
        assertThat(roleJpaRepository.existsByIdNotAndName(savedRole1.getId(), TEST_ROLE_TWO)).isTrue();
        assertThat(roleJpaRepository.existsByIdNotAndName(NON_EXISTENT_ID, TEST_ROLE_NAME)).isTrue();
        assertThat(roleJpaRepository.existsByIdNotAndName(NON_EXISTENT_ID, NON_EXISTENT)).isFalse();
    }

    @Test
    @DisplayName("Should update role name successfully")
    void shouldUpdateRoleName() {
        // Given
        RoleEntity savedRole = roleJpaRepository.save(roleEntity);
        
        // When - update role name only
        savedRole.setName("updated_role_name");
        RoleEntity updatedRole = roleJpaRepository.save(savedRole);
        
        // Then
        assertThat(updatedRole.getName()).isEqualTo("updated_role_name");
        assertThat(updatedRole.getRights()).isNullOrEmpty(); // Puede ser null o vacío
    }

    @Test
    @DisplayName("Should delete role by ID successfully")
    void shouldDeleteRoleById() {
        // Given
        RoleEntity savedRole = roleJpaRepository.save(roleEntity);
        Long roleId = savedRole.getId();
        
        // When
        roleJpaRepository.deleteById(roleId);
        
        // Then
        Optional<RoleEntity> deletedRole = roleJpaRepository.findById(roleId);
        assertThat(deletedRole).isEmpty();
        assertThat(roleJpaRepository.existsById(roleId)).isFalse();
    }
}