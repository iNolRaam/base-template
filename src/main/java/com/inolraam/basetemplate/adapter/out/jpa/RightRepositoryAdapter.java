package com.inolraam.basetemplate.adapter.out.jpa;

import com.inolraam.basetemplate.adapter.out.jpa.entity.RightEntity;
import com.inolraam.basetemplate.adapter.out.jpa.mapper.RightEntityMapper;
import com.inolraam.basetemplate.adapter.out.jpa.repository.RightJpaRepository;
import com.inolraam.basetemplate.domain.Right;
import com.inolraam.basetemplate.domain.port.RightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RightRepositoryAdapter implements RightRepository {

    private final RightJpaRepository rightJpaRepository;

    @Override
    public Right save(Right right) {
        final RightEntity rightEntity = RightEntityMapper.toEntity(right);
        final RightEntity saved = rightJpaRepository.save(rightEntity);
        return RightEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Right> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Right> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Right> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(long id) {

    }
}
