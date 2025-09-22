package com.inolraam.basetemplate.usecase;

/**
 * Base marker interface for all use cases.
 */
public interface UseCase<I, O> {
    O execute(I input);
}