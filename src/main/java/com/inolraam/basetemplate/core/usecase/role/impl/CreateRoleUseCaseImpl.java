package com.inolraam.basetemplate.core.usecase.role.impl;


import com.inolraam.basetemplate.core.domain.model.Role;
import com.inolraam.basetemplate.core.domain.port.inbound.role.CreateRoleUseCase;
import com.inolraam.basetemplate.core.domain.port.outbound.RoleRepository;
import com.inolraam.basetemplate.core.domain.service.RoleValidator;
import com.inolraam.basetemplate.core.usecase.role.dto.RoleInput;
import com.inolraam.basetemplate.core.usecase.role.dto.RoleOutput;
import com.inolraam.basetemplate.core.usecase.role.mapper.RoleDomainMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateRoleUseCaseImpl implements CreateRoleUseCase {
    private final RoleRepository roleRepository;
    private final RoleValidator roleValidator;

    @Override
    @Transactional
    public RoleOutput execute(RoleInput input) {
        final Role newData = RoleDomainMapper.toDomain(input);
        roleValidator.validateCreationAllowed(newData.getName(), newData.getRights());
        Role persisted = roleRepository.save(newData);
        return RoleDomainMapper.toOutput(persisted);
    }
}