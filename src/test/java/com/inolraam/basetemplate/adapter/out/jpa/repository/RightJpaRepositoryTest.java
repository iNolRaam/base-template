package com.inolraam.basetemplate.adapter.out.jpa.repository;

import com.inolraam.basetemplate.adapter.out.jpa.entity.RightEntity;
import com.inolraam.basetemplate.adapter.out.jpa.entity.TypeRightEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class RightJpaRepositoryTest extends BaseRepositoryTest {
    @Autowired
    private RightJpaRepository rightJpaRepository;

    @Autowired 
    private TypeRightJpaRepository typeRightJpaRepository;

    private RightEntity testEntity;
    private TypeRightEntity typeRightEntity;

    @BeforeEach
    void setUp() {
        // Create and save type right first since it's required for right
        typeRightEntity = new TypeRightEntity();
        typeRightEntity.setName("test_right_type_right");
        typeRightEntity.setVisible(true);
        typeRightEntity.setCreatedBy("test");
        typeRightEntity.setCreatedAt(new Date());
        typeRightEntity = typeRightJpaRepository.save(typeRightEntity);

        // Create right entity for testing
        testEntity = new RightEntity();
        testEntity.setName("test_right");
        testEntity.setVisible(true);
        testEntity.setCreatedBy("test");
        testEntity.setCreatedAt(new Date());
        testEntity.setIdTypeRight(typeRightEntity);
    }

    @Test
    void shouldSaveRight() {
        RightEntity savedEntity = rightJpaRepository.save(testEntity);
        
        assertThat(savedEntity).isNotNull();
        assertThat(savedEntity.getId()).isNotNull();
        assertThat(savedEntity.getName()).isEqualTo(testEntity.getName());
        assertThat(savedEntity.getIdTypeRight().getId()).isEqualTo(typeRightEntity.getId());
    }

    @Test
    void shouldFindRightById() {
        RightEntity savedEntity = rightJpaRepository.save(testEntity);
        
        Optional<RightEntity> found = rightJpaRepository.findById(savedEntity.getId());
        
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo(testEntity.getName());
        assertThat(found.get().getIdTypeRight().getId()).isEqualTo(typeRightEntity.getId());
    }

    @Test
    void shouldCheckIfExistsByName() {
        rightJpaRepository.save(testEntity);

        boolean exists = rightJpaRepository.existsByName("test_right");
        boolean nonExistent = rightJpaRepository.existsByName("non_existent");

        assertThat(exists).isTrue();
        assertThat(nonExistent).isFalse();
    }

    @Test
    void shouldCheckIfExistsByIdNotAndName() {
        RightEntity savedEntity1 = rightJpaRepository.save(testEntity);

        RightEntity anotherEntity = new RightEntity();
        anotherEntity.setName("another_test_right");
        anotherEntity.setVisible(true);
        anotherEntity.setCreatedBy("test");
        anotherEntity.setCreatedAt(new Date());
        anotherEntity.setIdTypeRight(typeRightEntity);
        RightEntity savedEntity2 = rightJpaRepository.save(anotherEntity);

        boolean exists = rightJpaRepository.existsByIdNotAndName(savedEntity2.getId(), savedEntity1.getName());
        boolean nonExistent = rightJpaRepository.existsByIdNotAndName(savedEntity1.getId(), savedEntity1.getName());
        boolean nonExistent2 = rightJpaRepository.existsByIdNotAndName(savedEntity1.getId(), "some_other_name");

        assertThat(exists).isTrue();
        assertThat(nonExistent).isFalse();
        assertThat(nonExistent2).isFalse();
    }

    @Test
    void shouldCheckIfExistsByIdTypeRight() {
        rightJpaRepository.save(testEntity);

        boolean exists = rightJpaRepository.existsByIdTypeRight(typeRightEntity);
        TypeRightEntity nonExistentTypeRight = new TypeRightEntity();
        nonExistentTypeRight.setId(999L);
        boolean nonExistent = rightJpaRepository.existsByIdTypeRight(nonExistentTypeRight);

        assertThat(exists).isTrue();
        assertThat(nonExistent).isFalse();
    }
}
