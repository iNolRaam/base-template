package com.inolraam.basetemplate.adapter.out.jpa;

import com.inolraam.basetemplate.adapter.out.jpa.entity.RightEntity;
import com.inolraam.basetemplate.adapter.out.jpa.repository.RoleRightJpaRepository;
import com.inolraam.basetemplate.domain.port.RoleRightRepository;
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