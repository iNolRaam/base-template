package com.inolraam.basetemplate.adapter.out.jpa;

import com.inolraam.basetemplate.adapter.out.jpa.entity.TypeRightEntity;
import com.inolraam.basetemplate.adapter.out.jpa.mapper.TypeRightMapper;
import com.inolraam.basetemplate.adapter.out.jpa.repository.TypeRightJpaRepository;
import com.inolraam.basetemplate.common.exception.NotFoundException;
import com.inolraam.basetemplate.domain.TypeRight;
import com.inolraam.basetemplate.domain.port.TypeRightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TypeRightRepositoryAdapter implements TypeRightRepository {

    private final TypeRightJpaRepository typeRightJpaRep;

    @Override
    public TypeRight save(TypeRight typeRight) {
        TypeRightEntity entity = TypeRightMapper.toEntity(typeRight);
        TypeRightEntity saved = typeRightJpaRep.save(entity);
        return TypeRightMapper.toDomain(saved);
    }

    @Override
    public TypeRight findById(long id) {
        return typeRightJpaRep.findById(id).map(TypeRightMapper::toDomain).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public TypeRight findByName(String name) {
        return typeRightJpaRep.findByName(name).map(TypeRightMapper::toDomain).orElseThrow(() -> new NotFoundException(name));
    }

    @Override
    public List<TypeRight> findAll() {
        return typeRightJpaRep.findAll().stream().map(TypeRightMapper::toDomain).toList();
    }

    @Override
    public boolean existsByName(String name) {
        return typeRightJpaRep.existsByName(name);
    }

    @Override
    public boolean existsById(long id) {
        return typeRightJpaRep.existsById(id);
    }

    @Override
    public void deleteById(long id) {
        typeRightJpaRep.deleteById(id);
    }
}
