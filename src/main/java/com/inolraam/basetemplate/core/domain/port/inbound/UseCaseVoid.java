package com.inolraam.basetemplate.core.domain.port.inbound;

public interface UseCaseVoid<I> {
    void execute(I input);
}
