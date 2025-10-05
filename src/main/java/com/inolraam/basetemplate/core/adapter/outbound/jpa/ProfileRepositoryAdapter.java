package com.inolraam.basetemplate.core.adapter.outbound.jpa;

import com.inolraam.basetemplate.core.adapter.outbound.jpa.entity.ProfileEntity;
import com.inolraam.basetemplate.core.adapter.outbound.jpa.mapper.ProfileEntityMapper;
import com.inolraam.basetemplate.core.adapter.outbound.jpa.repository.ProfileJpaRepository;
import com.inolraam.basetemplate.core.domain.model.Profile;
import com.inolraam.basetemplate.core.domain.port.outbound.ProfileRepository;
import com.inolraam.basetemplate.shared.common.constant.EntityType;
import com.inolraam.basetemplate.shared.common.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProfileRepositoryAdapter implements ProfileRepository {

    private final ProfileJpaRepository profileJpaRepository;

    @Override
    public Profile save(Profile profile) {
        ProfileEntity entity = ProfileEntityMapper.toEntity(profile);
        ProfileEntity saved = profileJpaRepository.save(entity);
        return ProfileEntityMapper.toDomain(saved);
    }

    @Override
    public Profile update(Profile profile) {
        return this.save(profile);
    }

    @Override
    public Profile findById(long id) {
        return profileJpaRepository.findById(id).map(ProfileEntityMapper::toDomain)
                .orElseThrow(() -> new NotFoundException(EntityType.PROFILE, id));
    }

    @Override
    public Profile findByName(String name) {
        return profileJpaRepository.findByName(name).map(ProfileEntityMapper::toDomain)
                .orElseThrow(() -> new NotFoundException(EntityType.PROFILE, name));
    }

    @Override
    public List<Profile> findAll() {
        return profileJpaRepository.findAll().stream().map(ProfileEntityMapper::toDomain).toList();
    }

    @Override
    public boolean existsByName(String name) {
        return profileJpaRepository.existsByName(name);
    }

    @Override
    public boolean existsById(long id) {
        return profileJpaRepository.existsById(id);
    }

    @Override
    public boolean existsByIdNotAndName(long id, String name) {
        return profileJpaRepository.existsByIdNotAndName(id, name);
    }

    @Override
    public void deleteById(long id) {
        profileJpaRepository.deleteById(id);
    }
}