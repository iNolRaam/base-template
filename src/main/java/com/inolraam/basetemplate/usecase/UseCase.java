package com.inolraam.basetemplate.usecase;

public interface UseCase <I, O> {
    O execute(I input);
}
