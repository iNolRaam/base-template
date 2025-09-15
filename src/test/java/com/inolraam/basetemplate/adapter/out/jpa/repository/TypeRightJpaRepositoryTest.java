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
}