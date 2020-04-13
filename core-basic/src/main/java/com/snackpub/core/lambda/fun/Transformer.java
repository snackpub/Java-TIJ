package com.snackpub.core.lambda.fun;

@FunctionalInterface
public interface Transformer<T> {

    T transformer(T input);
}
