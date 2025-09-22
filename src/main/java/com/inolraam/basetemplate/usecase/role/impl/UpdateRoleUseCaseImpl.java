package com.inolraam.basetemplate.usecase.role.impl;

import com.inolraam.basetemplate.domain.Role;
import com.inolraam.basetemplate.domain.port.RoleRepository;
import com.inolraam.basetemplate.domain.service.RoleValidator;
import com.inolraam.basetemplate.usecase.role.UpdateRoleUseCase;
import com.inolraam.basetemplate.usecase.role.dto.RoleOutput;
import com.inolraam.basetemplate.usecase.role.dto.UpdateRoleInput;
import com.inolraam.basetemplate.usecase.role.mapper.RoleDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateRoleUseCaseImpl implements UpdateRoleUseCase {

    private final RoleRepository roleRepository;
    private final RoleValidator roleValidator;

    @Override
    @Transactional
    public RoleOutput execute(UpdateRoleInput input) {
        final Role newData = RoleDomainMapper.toDomain(input.id(), input.roleInput());
        roleValidator.validateUpdatingAllowed(newData.getId(), newData.getName(), newData.getRights());
        final Role original = roleRepository.findById(newData.getId());
        final Role persisted = roleRepository.update(original.updateWith(newData));
        return RoleDomainMapper.toOutput(persisted);
    }
}