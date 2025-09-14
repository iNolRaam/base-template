package com.inolraam.basetemplate.adapter.out.jpa;

import com.inolraam.basetemplate.adapter.out.jpa.entity.RightEntity;
import com.inolraam.basetemplate.adapter.out.jpa.entity.TypeRightEntity;
import com.inolraam.basetemplate.adapter.out.jpa.mapper.RightEntityMapper;
import com.inolraam.basetemplate.adapter.out.jpa.repository.RightJpaRepository;
import com.inolraam.basetemplate.common.constant.EntityType;
import com.inolraam.basetemplate.common.exception.NotFoundException;
import com.inolraam.basetemplate.domain.Right;
import com.inolraam.basetemplate.domain.port.RightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RightRepositoryAdapter implements RightRepository {

    private final RightJpaRepository rightJpaRep;

    @Override
    public Right save(Right right) {
        final RightEntity rightEntity = RightEntityMapper.toEntity(right);
        final RightEntity saved = rightJpaRep.save(rightEntity);
        return RightEntityMapper.toDomain(saved);
    }

    @Override
    public Right findById(long id) {
        return rightJpaRep.findById(id)
                .map(RightEntityMapper::toDomain)
                .orElseThrow(() -> new NotFoundException(EntityType.RIGHT, id));
    }

    @Override
    public boolean existsById(long id) {
        return rightJpaRep.existsById(id);
    }

    @Override
    public Right findByName(String name) {
        return rightJpaRep.findByName(name)
                .map(RightEntityMapper::toDomain).orElseThrow(() -> new NotFoundException(EntityType.RIGHT, name));
    }

    @Override
    public List<Right> findAll() {
        return rightJpaRep.findAll().stream()
                .map(RightEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(long id) {
        rightJpaRep.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return rightJpaRep.existsByName(name);
    }

    @Override
    public boolean existsByIdNotAndName(long id, String name) {
        return rightJpaRep.existsByIdNotAndName(id, name);
    }

    @Override
    public boolean existsByIdTypeRight(long idTypeRight) {
        return rightJpaRep.existsByIdTypeRight(new TypeRightEntity(idTypeRight));
    }
}
