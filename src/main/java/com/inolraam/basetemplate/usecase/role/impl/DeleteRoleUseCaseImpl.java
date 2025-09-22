package com.inolraam.basetemplate.usecase.role.impl;

import com.inolraam.basetemplate.domain.port.RoleRepository;
import com.inolraam.basetemplate.domain.service.RoleValidator;
import com.inolraam.basetemplate.usecase.role.DeleteRoleUseCase;
import com.inolraam.basetemplate.usecase.role.dto.RoleIDInput;

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
    public void execute(RoleIDInput idInput) {
        roleValidator.validateDeletionAllowed(idInput.getId());
        roleRepository.deleteById(idInput.getId());
    }
}