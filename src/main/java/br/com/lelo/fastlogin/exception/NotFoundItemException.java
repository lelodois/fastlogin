package br.com.lelo.fastlogin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundItemException extends RuntimeException {

    private static final long serialVersionUID = 821912327245997992L;

    public NotFoundItemException() {
        super("item não encontrado");
    }

}
