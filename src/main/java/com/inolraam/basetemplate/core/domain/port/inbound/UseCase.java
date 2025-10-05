package com.inolraam.basetemplate.core.domain.port.inbound;

/**
 * Base marker interface for all use cases.
 */
public interface UseCase<I, O> {
    O execute(I input);
}