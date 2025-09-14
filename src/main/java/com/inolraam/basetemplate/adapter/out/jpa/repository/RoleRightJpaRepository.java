package com.inolraam.basetemplate.adapter.out.jpa.repository;

import com.inolraam.basetemplate.adapter.out.jpa.entity.RightEntity;
import com.inolraam.basetemplate.adapter.out.jpa.entity.RoleRightEntity;
import com.inolraam.basetemplate.adapter.out.jpa.entity.RoleRightPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRightJpaRepository extends JpaRepository<RoleRightEntity, RoleRightPK> {
    
    boolean existsByIdRight(RightEntity idRight);
}