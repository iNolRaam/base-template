package com.inolraam.basetemplate.adapter.out.jpa.repository;

import com.inolraam.basetemplate.adapter.out.jpa.entity.RightEntity;
import com.inolraam.basetemplate.adapter.out.jpa.entity.TypeRightEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RightJpaRepositoryTest extends BaseRepositoryTest {
    private static final String TEST_TYPE_RIGHT = "test_type_right";
    private static final String TEST_RIGHT = "test_right";
    private static final String TEST_RIGHT_TWO = "test_right_two";
    private static final String CREATED_BY = "user_test";
    private static final String NON_EXISTENT = "non_existent";
    private static final long NON_EXISTENT_ID = 999L;

    @Autowired
    private RightJpaRepository rightJpaRepository;

    @Autowired
    private TypeRightJpaRepository typeRightJpaRepository;

    private RightEntity rightEntity;
    private RightEntity rightTwoEntity;
    private TypeRightEntity typeRightEntity;

    @BeforeEach
    void setUp() {
        // Create and save type right first since it's required for right
        typeRightEntity = new TypeRightEntity();
        typeRightEntity.setName(TEST_TYPE_RIGHT);
        typeRightEntity.setVisible(true);
        typeRightEntity.setCreatedBy(CREATED_BY);
        typeRightEntity.setCreatedAt(LocalDateTime.now());
        typeRightEntity = typeRightJpaRepository.save(typeRightEntity);

        // Create right entity for testing
        rightEntity = new RightEntity();
        rightEntity.setName(TEST_RIGHT);
        rightEntity.setVisible(true);
        rightEntity.setCreatedBy(CREATED_BY);
        rightEntity.setCreatedAt(LocalDateTime.now());
        rightEntity.setIdTypeRight(typeRightEntity);

        rightTwoEntity = new RightEntity();
        rightTwoEntity.setName(TEST_RIGHT_TWO);
        rightTwoEntity.setVisible(true);
        rightTwoEntity.setCreatedBy(CREATED_BY);
        rightTwoEntity.setCreatedAt(LocalDateTime.now());
        rightTwoEntity.setIdTypeRight(typeRightEntity);
    }

    @Test
    void shouldSaveRight() {
        RightEntity savedEntity = rightJpaRepository.save(rightEntity);

        assertThat(savedEntity).isNotNull();
        assertThat(savedEntity.getId()).isNotNull();
        assertThat(savedEntity.getName()).isEqualTo(rightEntity.getName());
        assertThat(savedEntity.getIdTypeRight().getId()).isEqualTo(typeRightEntity.getId());
    }

    @Test
    void shouldFindRightById() {
        RightEntity savedEntity = rightJpaRepository.save(rightEntity);

        Optional<RightEntity> found = rightJpaRepository.findById(savedEntity.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo(rightEntity.getName());
        assertThat(found.get().getIdTypeRight().getId()).isEqualTo(typeRightEntity.getId());
    }

    @Test
    void shouldCheckIfExistsByName() {
        rightJpaRepository.save(rightEntity);

        boolean exists = rightJpaRepository.existsByName(TEST_RIGHT);
        boolean nonExistent = rightJpaRepository.existsByName(NON_EXISTENT);

        assertThat(exists).isTrue();
        assertThat(nonExistent).isFalse();
    }

    @Test
    void shouldCheckIfExistsByIdNotAndName() {
        RightEntity savedEntity1 = rightJpaRepository.save(rightEntity);
        RightEntity savedEntity2 = rightJpaRepository.save(rightTwoEntity);

        boolean exists = rightJpaRepository.existsByIdNotAndName(savedEntity2.getId(), savedEntity1.getName());
        boolean nonExistent = rightJpaRepository.existsByIdNotAndName(savedEntity1.getId(), savedEntity1.getName());
        boolean nonExistent2 = rightJpaRepository.existsByIdNotAndName(savedEntity1.getId(), NON_EXISTENT);

        // delete to clean up
        rightJpaRepository.deleteById(savedEntity2.getId());

        assertThat(exists).isTrue();
        assertThat(nonExistent).isFalse();
        assertThat(nonExistent2).isFalse();
    }

    @Test
    void shouldCheckIfExistsByIdTypeRight() {
        rightJpaRepository.save(rightEntity);

        boolean exists = rightJpaRepository.existsByIdTypeRight(typeRightEntity);
        TypeRightEntity nonExistentTypeRight = new TypeRightEntity();
        nonExistentTypeRight.setId(NON_EXISTENT_ID);
        boolean nonExistent = rightJpaRepository.existsByIdTypeRight(nonExistentTypeRight);

        assertThat(exists).isTrue();
        assertThat(nonExistent).isFalse();
    }

    @Test
    void shouldFailWhenSavingRightWithDuplicateName() {
        // First save
        rightJpaRepository.save(rightEntity);
        rightJpaRepository.flush(); // Important: force write to DB

        // Create another entity with the same name
        RightEntity duplicateRight = new RightEntity();
        duplicateRight.setName(TEST_RIGHT); // Same name as rightEntity
        duplicateRight.setVisible(true);
        duplicateRight.setCreatedBy(CREATED_BY);
        duplicateRight.setCreatedAt(LocalDateTime.now());
        duplicateRight.setIdTypeRight(typeRightEntity);

        // This should throw DataIntegrityViolationException
        assertThatThrownBy(() -> {
            rightJpaRepository.save(duplicateRight);
            rightJpaRepository.flush(); // Important: force write to DB
        }).isInstanceOf(DataIntegrityViolationException.class);
    }
}
