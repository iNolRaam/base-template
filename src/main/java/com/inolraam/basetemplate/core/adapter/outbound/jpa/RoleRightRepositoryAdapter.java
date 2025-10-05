package com.inolraam.basetemplate.core.adapter.outbound.jpa;

import com.inolraam.basetemplate.core.adapter.outbound.jpa.entity.RightEntity;
import com.inolraam.basetemplate.core.adapter.outbound.jpa.repository.RoleRightJpaRepository;
import com.inolraam.basetemplate.core.domain.port.outbound.RoleRightRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleRightRepositoryAdapter implements RoleRightRepository {
    private final RoleRightJpaRepository roleRightJpaRepository;

    @Override
    public boolean existsByIdRight(long idRight) {
        return roleRightJpaRepository.existsByIdRight(new RightEntity(idRight));
    }
}