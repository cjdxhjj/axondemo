package com.example.demo.domain.user.event;

public record UserCreateEvent(long id, String name, int age, String address) {}
