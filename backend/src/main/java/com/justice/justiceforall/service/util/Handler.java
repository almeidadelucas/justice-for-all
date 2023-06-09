package com.justice.justiceforall.service.util;

public interface Handler<T> {

    void setNext(Handler<T> next);

    void validate(T input);
}
