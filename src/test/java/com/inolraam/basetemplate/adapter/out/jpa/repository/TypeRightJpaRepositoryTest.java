package com.inolraam.basetemplate.adapter.out.jpa.repository;

import com.inolraam.basetemplate.adapter.out.jpa.entity.TypeRightEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TypeRightJpaRepositoryTest extends BaseRepositoryTest {
    private static final String TEST_TYPE_RIGHT ="test_type_right";
    private static final String TEST_TYPE_RIGHT_TWO ="test_type_right_two";
    private static final String CREATED_BY ="user_test";
    private static final String NON_EXISTENT ="non_existent";

    @Autowired
    private TypeRightJpaRepository typeRightJpaRepository;

    private TypeRightEntity typeRightEntity;
    private TypeRightEntity typeRightTwoEntity;

    @BeforeEach
    void setUp() {
        typeRightEntity = new TypeRightEntity();
        typeRightEntity.setName(TEST_TYPE_RIGHT);
        typeRightEntity.setVisible(true);
        typeRightEntity.setCreatedBy(CREATED_BY);
        typeRightEntity.setCreatedAt(LocalDateTime.now());

        typeRightTwoEntity = new TypeRightEntity();
        typeRightTwoEntity.setName(TEST_TYPE_RIGHT_TWO);
        typeRightTwoEntity.setVisible(true);
        typeRightTwoEntity.setCreatedBy(CREATED_BY);
        typeRightTwoEntity.setCreatedAt(LocalDateTime.now());
    }


    @Test
    void shouldSaveTypeRight() {
        TypeRightEntity savedEntity = typeRightJpaRepository.save(typeRightEntity);
        
        assertThat(savedEntity).isNotNull();
        assertThat(savedEntity.getId()).isNotNull();
        assertThat(savedEntity.getName()).isEqualTo(typeRightEntity.getName());
    }

    @Test
    void shouldFindTypeRightById() {
        TypeRightEntity savedEntity = typeRightJpaRepository.save(typeRightEntity);
        
        Optional<TypeRightEntity> found = typeRightJpaRepository.findById(savedEntity.getId());
        
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo(typeRightEntity.getName());
    }

    @Test
    void shouldCheckIfExistsByName() {
        typeRightJpaRepository.save(typeRightEntity);

        boolean exists = typeRightJpaRepository.existsByName(TEST_TYPE_RIGHT);
        boolean nonExistent = typeRightJpaRepository.existsByName(NON_EXISTENT);

        assertThat(exists).isTrue();
        assertThat(nonExistent).isFalse();
    }

    @Test
    void shouldCheckIfExistsByIdNotAndName() {
        TypeRightEntity savedEntity1 = typeRightJpaRepository.save(typeRightEntity);
        TypeRightEntity savedEntity2 = typeRightJpaRepository.save(typeRightTwoEntity);

        boolean exists = typeRightJpaRepository.existsByIdNotAndName(savedEntity2.getId(), savedEntity1.getName());
        boolean nonExistent = typeRightJpaRepository.existsByIdNotAndName(savedEntity1.getId(), savedEntity1.getName());
        boolean nonExistent2 = typeRightJpaRepository.existsByIdNotAndName(savedEntity1.getId(), NON_EXISTENT);

        assertThat(exists).isTrue();
        assertThat(nonExistent).isFalse();
        assertThat(nonExistent2).isFalse();
    }

    @Test
    void shouldFailWhenSavingRightWithDuplicateName() {
        // First save
        typeRightJpaRepository.save(typeRightEntity);
        typeRightJpaRepository.flush(); // Important: force write to DB

        // Create another entity with the same name
        TypeRightEntity duplicateRight = new TypeRightEntity();
        duplicateRight.setName(TEST_TYPE_RIGHT); // Same name as rightEntity
        duplicateRight.setVisible(true);
        duplicateRight.setCreatedBy(CREATED_BY);
        duplicateRight.setCreatedAt(LocalDateTime.now());

        // This should throw DataIntegrityViolationException
        assertThatThrownBy(() -> {
            typeRightJpaRepository.save(duplicateRight);
            typeRightJpaRepository.flush(); // Important: force write to DB
        }).isInstanceOf(DataIntegrityViolationException.class);
    }
}