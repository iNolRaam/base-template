package com.inolraam.basetemplate.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Profile extends BaseCatalog {

    private final Set<Long> roles = new HashSet<>();

    public void addRole(Long role) {
        roles.add(role);
    }

    public void removeRole(Long role) {
        roles.remove(role);
    }

    public Set<Long> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public Profile updateWith(Profile newData) {
        // Create updated profile with new data
        Profile updatedProfile = Profile.builder()
                .id(this.getId())
                .name(newData.getName())
                .visible(newData.getVisible())
                .build();
        
        // Add roles from new data
        if (newData.getRoles() != null) {
            newData.getRoles().forEach(updatedProfile::addRole);
        }
        
        return updatedProfile;
    }
}
