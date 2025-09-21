package com.inolraam.basetemplate.usecase;

public interface UseCase<I, O> extends GenericUseCase {
    O execute(I input);
}
