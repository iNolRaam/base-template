package com.inolraam.basetemplate.adapter.out.jpa.repository;

import com.inolraam.basetemplate.adapter.out.jpa.entity.TypeRightEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class TypeRightJpaRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private TypeRightJpaRepository typeRightJpaRepository;

    private TypeRightEntity testEntity;

    @BeforeEach
    void setUp() {
        testEntity = new TypeRightEntity();
        testEntity.setName("TEST_TYPE");
        testEntity.setVisible(true);
        testEntity.setCreatedBy("test");
        testEntity.setCreatedAt(new Date());
    }

    @Test
    void shouldSaveTypeRight() {
        TypeRightEntity savedEntity = typeRightJpaRepository.save(testEntity);
        
        assertThat(savedEntity).isNotNull();
        assertThat(savedEntity.getId()).isNotNull();
        assertThat(savedEntity.getName()).isEqualTo(testEntity.getName());
    }

    @Test
    void shouldFindTypeRightById() {
        TypeRightEntity savedEntity = typeRightJpaRepository.save(testEntity);
        
        Optional<TypeRightEntity> found = typeRightJpaRepository.findById(savedEntity.getId());
        
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo(testEntity.getName());
    }

    @Test
    void shouldCheckIfExistsByName() {
        typeRightJpaRepository.save(testEntity);

        boolean exists = typeRightJpaRepository.existsByName("TEST_TYPE");
        boolean nonExistent = typeRightJpaRepository.existsByName("NON_EXISTENT");

        assertThat(exists).isTrue();
        assertThat(nonExistent).isFalse();
    }

    @Test
    void shouldCheckIfExistsByIdNotAndName() {
        TypeRightEntity savedEntity1 = typeRightJpaRepository.save(testEntity);

        TypeRightEntity anotherEntity = new TypeRightEntity();
        anotherEntity.setName("ANOTHER_TEST_TYPE");
        anotherEntity.setVisible(true);
        anotherEntity.setCreatedBy("test");
        anotherEntity.setCreatedAt(new Date());
        TypeRightEntity savedEntity2 = typeRightJpaRepository.save(anotherEntity);

        boolean exists = typeRightJpaRepository.existsByIdNotAndName(savedEntity2.getId(), savedEntity1.getName());
        boolean nonExistent = typeRightJpaRepository.existsByIdNotAndName(savedEntity1.getId(), savedEntity1.getName());
        boolean nonExistent2 = typeRightJpaRepository.existsByIdNotAndName(savedEntity1.getId(), "some_other_name");

        assertThat(exists).isTrue();
        assertThat(nonExistent).isFalse();
        assertThat(nonExistent2).isFalse();
    }
}