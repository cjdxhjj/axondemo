package com.example.demo.domain.shared.entity;

public record ActionResult<T>(int code, T data, String error) {
    public ActionResult(int code, String error) {
        this(code, null, error);
    }

    public ActionResult(T data) {
        this(0, data, "");
    }
}
