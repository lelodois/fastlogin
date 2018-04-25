package br.com.lelo.fastlogin.common;

import java.util.Optional;

import br.com.lelo.fastlogin.exception.NotFoundItemException;

public class PresentObject<T> {

    public T getOrThrow(Optional<T> optional) {
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NotFoundItemException();
    }
}
