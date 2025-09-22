package com.inolraam.basetemplate.adapter.out.jpa;

import com.inolraam.basetemplate.adapter.out.jpa.entity.RoleEntity;
import com.inolraam.basetemplate.adapter.out.jpa.mapper.RoleEntityMapper;
import com.inolraam.basetemplate.adapter.out.jpa.repository.RoleJpaRepository;
import com.inolraam.basetemplate.common.constant.EntityType;
import com.inolraam.basetemplate.common.exception.NotFoundException;
import com.inolraam.basetemplate.domain.Role;
import com.inolraam.basetemplate.domain.port.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;

    @Override
    public Role save(Role role) {
        RoleEntity entity = RoleEntityMapper.toEntity(role);
        RoleEntity saved = roleJpaRepository.save(entity);
        return RoleEntityMapper.toDomain(saved);
    }

    @Override
    public Role update(Role role) {
        return this.save(role);
    }

    @Override
    public Role findById(long id) {
        return roleJpaRepository.findById(id).map(RoleEntityMapper::toDomain)
                .orElseThrow(() -> new NotFoundException(EntityType.ROLE, id));
    }

    @Override
    public Role findByName(String name) {
        return roleJpaRepository.findByName(name).map(RoleEntityMapper::toDomain)
                .orElseThrow(() -> new NotFoundException(EntityType.ROLE, name));
    }

    @Override
    public boolean existsById(long id) {
        return roleJpaRepository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return roleJpaRepository.existsByName(name);
    }

    @Override
    public boolean existsByIdNotAndName(long id, String name) {
        return roleJpaRepository.existsByIdNotAndName(id, name);
    }

    @Override
    public void deleteById(long id) {
        roleJpaRepository.deleteById(id);
    }
}