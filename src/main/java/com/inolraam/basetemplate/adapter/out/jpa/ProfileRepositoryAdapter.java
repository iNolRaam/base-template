package com.inolraam.basetemplate.adapter.out.jpa;

import com.inolraam.basetemplate.adapter.out.jpa.entity.ProfileEntity;
import com.inolraam.basetemplate.adapter.out.jpa.mapper.ProfileEntityMapper;
import com.inolraam.basetemplate.adapter.out.jpa.repository.ProfileJpaRepository;
import com.inolraam.basetemplate.common.constant.EntityType;
import com.inolraam.basetemplate.common.exception.NotFoundException;
import com.inolraam.basetemplate.domain.Profile;
import com.inolraam.basetemplate.domain.port.ProfileRepository;
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