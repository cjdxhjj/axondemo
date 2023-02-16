package com.example.demo.domain.user.event;

public record UserUpdateEvent(long id, String name, int age, String address) {
}
