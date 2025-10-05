package com.inolraam.basetemplate.core.domain.port.inbound.user;

import com.inolraam.basetemplate.core.domain.port.inbound.UseCaseVoid;

/**
 * Use case interface for deleting users.
 * 
 * @author Generated
 * @version 1.0
 */
public interface DeleteUserUseCase extends UseCaseVoid<Long> {
    
    /**
     * Execute the delete user use case.
     * 
     * @param id the user ID to delete
     */
    void execute(Long id);
}