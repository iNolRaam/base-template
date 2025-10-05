package com.inolraam.basetemplate.core.domain.port.inbound.user;

import com.inolraam.basetemplate.core.domain.port.inbound.UseCase;
import com.inolraam.basetemplate.core.usecase.user.dto.UserOutput;

/**
 * Use case interface for reading users by ID.
 * 
 * @author Generated
 * @version 1.0
 */
public interface ReadUserUseCase extends UseCase<Long, UserOutput> {
    
    /**
     * Execute the read user use case.
     * 
     * @param id the user ID
     * @return the user output data
     */
    UserOutput execute(Long id);
}