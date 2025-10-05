package com.inolraam.basetemplate.core.usecase.role.impl;

import com.inolraam.basetemplate.core.domain.port.inbound.role.DeleteRoleUseCase;
import com.inolraam.basetemplate.core.domain.port.outbound.RoleRepository;
import com.inolraam.basetemplate.core.domain.service.RoleValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteRoleUseCaseImpl implements DeleteRoleUseCase {

    private final RoleRepository roleRepository;
    private final RoleValidator roleValidator;

    @Override
    @Transactional
    public void execute(Long id) {
        roleValidator.validateDeletionAllowed(id);
        roleRepository.deleteById(id);
    }
}