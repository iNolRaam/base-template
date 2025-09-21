package com.inolraam.basetemplate.usecase;

public interface UseCaseVoid<I> extends GenericUseCase {
    void execute(I input);
}
