package com.inolraam.basetemplate.core.usecase.role.impl;

import com.inolraam.basetemplate.core.domain.model.Role;
import com.inolraam.basetemplate.core.domain.port.inbound.role.ReadRoleUseCase;
import com.inolraam.basetemplate.core.domain.port.outbound.RoleRepository;
import com.inolraam.basetemplate.core.domain.service.RoleValidator;
import com.inolraam.basetemplate.core.usecase.role.dto.RoleOutput;
import com.inolraam.basetemplate.core.usecase.role.mapper.RoleDomainMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReadRoleUseCaseImpl implements ReadRoleUseCase {
    private final RoleRepository roleRepository;
    private final RoleValidator roleValidator;

    @Override
    @Transactional(readOnly = true)
    public RoleOutput execute(Long input) {
        roleValidator.validateReadingAllowed(input);
        final Role role = roleRepository.findById(input);
        return RoleDomainMapper.toOutput(role);
    }
}