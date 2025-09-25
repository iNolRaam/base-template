package com.inolraam.basetemplate.adapter.out.jpa.repository;

import com.inolraam.basetemplate.adapter.out.jpa.entity.ProfileEntity;
import com.inolraam.basetemplate.adapter.out.jpa.entity.RoleEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProfileJpaRepositoryTest extends BaseRepositoryTest {
    
    // Test constants
    private static final String TEST_PROFILE_NAME = "test_profile";
    private static final String TEST_PROFILE_TWO = "test_profile_two";
    private static final String TEST_ROLE_ONE = "test_role_one";
    private static final String TEST_ROLE_TWO = "test_role_two";
    private static final String CREATED_BY = "user_test";
    private static final String NON_EXISTENT = "non_existent";
    private static final long NON_EXISTENT_ID = 999L;

    @Autowired
    private ProfileJpaRepository profileJpaRepository;

    // We need to create roles directly with EntityManager since there's no RoleJpaRepository
    @Autowired
    private jakarta.persistence.EntityManager entityManager;

    // Test data
    private ProfileEntity profileEntity;
    private ProfileEntity profileTwoEntity;
    private RoleEntity roleOneEntity;
    private RoleEntity roleTwoEntity;

    @BeforeEach
    void setUp() {
        // Create role entities first (needed for referential integrity)
        roleOneEntity = new RoleEntity();
        roleOneEntity.setName(TEST_ROLE_ONE);
        roleOneEntity.setVisible(true);
        roleOneEntity.setCreatedBy(CREATED_BY);
        roleOneEntity.setCreatedAt(LocalDateTime.now());
        entityManager.persist(roleOneEntity);

        roleTwoEntity = new RoleEntity();
        roleTwoEntity.setName(TEST_ROLE_TWO);
        roleTwoEntity.setVisible(true);
        roleTwoEntity.setCreatedBy(CREATED_BY);
        roleTwoEntity.setCreatedAt(LocalDateTime.now());
        entityManager.persist(roleTwoEntity);

        entityManager.flush(); // Ensure roles are persisted with IDs

        // Create profile entity for testing
        profileEntity = new ProfileEntity();
        profileEntity.setName(TEST_PROFILE_NAME);
        profileEntity.setVisible(true);
        profileEntity.setCreatedBy(CREATED_BY);
        profileEntity.setCreatedAt(LocalDateTime.now());

        // Create second profile entity for testing
        profileTwoEntity = new ProfileEntity();
        profileTwoEntity.setName(TEST_PROFILE_TWO);
        profileTwoEntity.setVisible(true);
        profileTwoEntity.setCreatedBy(CREATED_BY);
        profileTwoEntity.setCreatedAt(LocalDateTime.now());
    }

    // Helper method to create ProfileEntity instances for testing
    private ProfileEntity createProfile(String name) {
        ProfileEntity profile = new ProfileEntity();
        profile.setName(name);
        profile.setVisible(true);
        profile.setCreatedBy(CREATED_BY);
        profile.setCreatedAt(LocalDateTime.now());
        return profile;
    }

    // === BASIC CRUD OPERATIONS ===
    
    @Test
    void shouldSaveProfile() {
        // Given - profile entity ready from setup
        
        // When
        ProfileEntity savedProfile = profileJpaRepository.save(profileEntity);
        
        // Then
        assertThat(savedProfile).isNotNull();
        assertThat(savedProfile.getId()).isPositive();
        assertThat(savedProfile.getName()).isEqualTo(TEST_PROFILE_NAME);
        assertThat(savedProfile.getVisible()).isTrue();
        assertThat(savedProfile.getCreatedBy()).isEqualTo(CREATED_BY);
        assertThat(savedProfile.getCreatedAt()).isNotNull();
    }

    @Test
    void shouldFindProfileById() {
        // Given
        ProfileEntity savedProfile = profileJpaRepository.save(profileEntity);
        
        // When
        var foundProfile = profileJpaRepository.findById(savedProfile.getId());
        
        // Then
        assertThat(foundProfile).isPresent();
        assertThat(foundProfile.get().getId()).isEqualTo(savedProfile.getId());
        assertThat(foundProfile.get().getName()).isEqualTo(TEST_PROFILE_NAME);
        assertThat(foundProfile.get().getVisible()).isTrue();
    }

    @Test
    void shouldNotFindProfileById_WhenIdDoesNotExist() {
        // When
        var foundProfile = profileJpaRepository.findById(NON_EXISTENT_ID);
        
        // Then
        assertThat(foundProfile).isEmpty();
    }

    @Test
    void shouldFindAllProfiles() {
        // Given
        profileJpaRepository.save(profileEntity);
        profileJpaRepository.save(profileTwoEntity);
        
        // When
        var profiles = profileJpaRepository.findAll();
        
        // Then
        assertThat(profiles).hasSize(2);
        assertThat(profiles)
            .extracting(ProfileEntity::getName)
            .containsExactlyInAnyOrder(TEST_PROFILE_NAME, TEST_PROFILE_TWO);
    }

    @Test
    void shouldDeleteProfile() {
        // Given
        ProfileEntity savedProfile = profileJpaRepository.save(profileEntity);
        
        // When
        profileJpaRepository.delete(savedProfile);
        
        // Then
        var foundProfile = profileJpaRepository.findById(savedProfile.getId());
        assertThat(foundProfile).isEmpty();
    }

    @Test
    void shouldDeleteProfileById() {
        // Given
        ProfileEntity savedProfile = profileJpaRepository.save(profileEntity);
        
        // When
        profileJpaRepository.deleteById(savedProfile.getId());
        
        // Then
        var foundProfile = profileJpaRepository.findById(savedProfile.getId());
        assertThat(foundProfile).isEmpty();
    }
    
    // === CUSTOM QUERY METHODS ===
    
    @Test
    void shouldFindProfileByName() {
        // Given
        profileJpaRepository.save(profileEntity);
        
        // When
        var foundProfile = profileJpaRepository.findByName(TEST_PROFILE_NAME);
        
        // Then
        assertThat(foundProfile).isPresent();
        assertThat(foundProfile.get().getName()).isEqualTo(TEST_PROFILE_NAME);
        assertThat(foundProfile.get().getVisible()).isTrue();
    }

    @Test
    void shouldNotFindProfileByName_WhenNameDoesNotExist() {
        // When
        var foundProfile = profileJpaRepository.findByName(NON_EXISTENT);
        
        // Then
        assertThat(foundProfile).isEmpty();
    }

    @Test
    void shouldCheckIfExistsByName() {
        // Given
        profileJpaRepository.save(profileEntity);
        
        // When & Then
        assertThat(profileJpaRepository.existsByName(TEST_PROFILE_NAME)).isTrue();
        assertThat(profileJpaRepository.existsByName(NON_EXISTENT)).isFalse();
    }

    @Test
    void shouldCheckIfExistsByIdNotAndName() {
        // Given
        ProfileEntity savedProfile = profileJpaRepository.save(profileEntity);
        ProfileEntity anotherProfile = profileJpaRepository.save(profileTwoEntity);
        
        // When & Then - same profile should not conflict with itself
        assertThat(profileJpaRepository.existsByIdNotAndName(savedProfile.getId(), TEST_PROFILE_NAME)).isFalse();
        
        // When & Then - different profiles with same name should conflict
        assertThat(profileJpaRepository.existsByIdNotAndName(anotherProfile.getId(), TEST_PROFILE_NAME)).isTrue();
        
        // When & Then - non-existent name should not conflict
        assertThat(profileJpaRepository.existsByIdNotAndName(savedProfile.getId(), NON_EXISTENT)).isFalse();
    }

    // ========== Validation and Constraint Tests ==========

    @Test
    @DisplayName("Should fail to save profile with null name")
    void shouldFailToSaveProfileWithNullName() {
        // Given
        ProfileEntity profile = createProfile(null);
        
        // When & Then
        assertThatThrownBy(() -> {
            profileJpaRepository.save(profile);
            profileJpaRepository.flush();
        }).isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("Should allow empty name but enforce database constraints")
    void shouldAllowEmptyNameButEnforceDatabaseConstraints() {
        // Given - Empty string is allowed at entity level but may have business implications
        ProfileEntity profile = createProfile("");
        
        // When - Save should succeed as empty string is not null
        ProfileEntity savedProfile = profileJpaRepository.save(profile);
        profileJpaRepository.flush();
        
        // Then - Profile should be saved successfully
        assertThat(savedProfile).isNotNull();
        assertThat(savedProfile.getId()).isPositive();
        assertThat(savedProfile.getName()).isEmpty();
    }

    @Test
    @DisplayName("Should fail to save profile with name exceeding maximum length")
    void shouldFailToSaveProfileWithNameTooLong() {
        // Given - Create a name longer than 100 characters
        String longName = "a".repeat(101);
        ProfileEntity profile = createProfile(longName);
        
        // When & Then
        assertThatThrownBy(() -> {
            profileJpaRepository.save(profile);
            profileJpaRepository.flush();
        }).isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("Should save profile with maximum allowed name length")
    void shouldSaveProfileWithMaximumNameLength() {
        // Given - Create a name with exactly 100 characters
        String maxName = "a".repeat(100);
        ProfileEntity profile = createProfile(maxName);
        
        // When
        ProfileEntity savedProfile = profileJpaRepository.save(profile);
        
        // Then
        assertThat(savedProfile).isNotNull();
        assertThat(savedProfile.getId()).isGreaterThan(0);
        assertThat(savedProfile.getName()).hasSize(100);
        assertThat(savedProfile.getName()).isEqualTo(maxName);
    }

    @Test
    @DisplayName("Should fail to save profile with null visible flag")
    void shouldFailToSaveProfileWithNullVisible() {
        // Given
        ProfileEntity profile = createProfile("Valid Name");
        profile.setVisible(null);
        
        // When & Then
        assertThatThrownBy(() -> {
            profileJpaRepository.save(profile);
            profileJpaRepository.flush();
        }).isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("Should fail to save duplicate profile names")
    void shouldFailToSaveDuplicateProfileNames() {
        // Given
        ProfileEntity profile1 = createProfile("Duplicate Name");
        ProfileEntity profile2 = createProfile("Duplicate Name");
        
        profileJpaRepository.save(profile1);
        
        // When & Then
        assertThatThrownBy(() -> {
            profileJpaRepository.save(profile2);
            profileJpaRepository.flush();
        }).isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("Should handle case sensitivity in profile names")
    void shouldHandleCaseSensitivityInProfileNames() {
        // Given
        ProfileEntity profile1 = createProfile("Test Profile");
        ProfileEntity profile2 = createProfile("test profile");
        ProfileEntity profile3 = createProfile("TEST PROFILE");
        
        profileJpaRepository.save(profile1);
        
        // When - Try to save profiles with different cases
        ProfileEntity savedProfile2 = profileJpaRepository.save(profile2);
        ProfileEntity savedProfile3 = profileJpaRepository.save(profile3);
        
        // Then - All should be saved as names are case-sensitive
        assertThat(savedProfile2.getId()).isGreaterThan(0);
        assertThat(savedProfile3.getId()).isGreaterThan(0);
        
        // Verify all three exist with different names
        List<ProfileEntity> allProfiles = profileJpaRepository.findAll();
        assertThat(allProfiles).hasSize(3);
        
        Set<String> names = allProfiles.stream()
                .map(ProfileEntity::getName)
                .collect(Collectors.toSet());
        assertThat(names).containsExactlyInAnyOrder("Test Profile", "test profile", "TEST PROFILE");
    }

    @Test
    @DisplayName("Should validate profile name trimming behavior")
    void shouldValidateProfileNameTrimmingBehavior() {
        // Given
        ProfileEntity profileWithSpaces = createProfile("  Profile With Spaces  ");
        
        // When
        ProfileEntity savedProfile = profileJpaRepository.save(profileWithSpaces);
        
        // Then - Name should be saved as-is (no automatic trimming by JPA)
        assertThat(savedProfile.getName()).isEqualTo("  Profile With Spaces  ");
        
        // Verify we can find it by exact name
        Optional<ProfileEntity> found = profileJpaRepository.findByName("  Profile With Spaces  ");
        assertThat(found).isPresent();
        
        // Verify we cannot find it by trimmed name
        Optional<ProfileEntity> notFound = profileJpaRepository.findByName("Profile With Spaces");
        assertThat(notFound).isEmpty();
    }
    
    // === ROLE RELATIONSHIP TESTS ===
    
    @Test
    @DisplayName("Should save and retrieve profile with empty roles collection")
    void shouldSaveProfileWithEmptyRoles() {
        // Given - profile entity with no roles assigned
        profileEntity.setRoles(new java.util.HashSet<>());
        
        // When
        ProfileEntity savedProfile = profileJpaRepository.save(profileEntity);
        profileJpaRepository.flush();
        
        // Then
        assertThat(savedProfile).isNotNull();
        assertThat(savedProfile.getId()).isGreaterThan(0);
        assertThat(savedProfile.getRoles()).isNotNull();
        assertThat(savedProfile.getRoles()).isEmpty();
        
        // Verify persistence by re-fetching
        Optional<ProfileEntity> retrieved = profileJpaRepository.findById(savedProfile.getId());
        assertThat(retrieved).isPresent();
        assertThat(retrieved.get().getRoles()).isEmpty();
        
        // Verify profile properties are correctly saved
        assertThat(retrieved.get().getName()).isEqualTo(TEST_PROFILE_NAME);
        assertThat(retrieved.get().getVisible()).isTrue();
        assertThat(retrieved.get().getCreatedBy()).isEqualTo(CREATED_BY);
    }
}