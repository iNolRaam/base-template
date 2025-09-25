package com.inolraam.basetemplate.usecase.profile;

import com.inolraam.basetemplate.adapter.out.jpa.entity.ProfileEntity;
import com.inolraam.basetemplate.adapter.out.jpa.repository.ProfileJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio optimizado para gestión de Profiles con cache habilitado.
 * 
 * Optimizaciones Spring Boot 4:
 * - Cache inteligente con Caffeine
 * - Operaciones transaccionales optimizadas
 * - Logging mejorado para monitoreo
 * 
 * @author BaseTemplate
 * @version 4.0.0
 * @since Spring Boot 4.0.0-M1
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProfileCacheService {

    private final ProfileJpaRepository profileRepository;

    /**
     * Busca un profile por ID con cache habilitado.
     * Cache: 'profiles' con TTL de 10 minutos.
     * 
     * @param id ID del profile
     * @return ProfileEntity si existe
     */
    @Cacheable(value = "profiles", key = "#id")
    public Optional<ProfileEntity> findById(Long id) {
        log.debug("Buscando profile por ID: {} (cache miss)", id);
        return profileRepository.findById(id);
    }

    /**
     * Busca todos los profiles con cache habilitado.
     * Cache: 'profiles' con key 'all'.
     * 
     * @return Lista de todos los profiles
     */
    @Cacheable(value = "profiles", key = "'all'")
    public List<ProfileEntity> findAll() {
        log.debug("Buscando todos los profiles (cache miss)");
        return profileRepository.findAll();
    }

    /**
     * Guarda un profile y limpia el cache relacionado.
     * 
     * @param profile Profile a guardar
     * @return Profile guardado
     */
    @Transactional
    @CacheEvict(value = "profiles", allEntries = true)
    public ProfileEntity save(ProfileEntity profile) {
        log.info("Guardando profile ID: {} (limpiando cache)", profile.getId());
        return profileRepository.save(profile);
    }

    /**
     * Elimina un profile por ID y limpia el cache.
     * 
     * @param id ID del profile a eliminar
     */
    @Transactional
    @CacheEvict(value = "profiles", allEntries = true)
    public void deleteById(Long id) {
        log.info("Eliminando profile ID: {} (limpiando cache)", id);
        profileRepository.deleteById(id);
    }

    /**
     * Obtiene el conteo total de profiles con cache.
     * 
     * @return Número total de profiles
     */
    @Cacheable(value = "profiles", key = "'count'")
    public long count() {
        log.debug("Contando profiles (cache miss)");
        return profileRepository.count();
    }
}