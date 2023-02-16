package com.example.demo.domain.user.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateUserCommand {
    @TargetAggregateIdentifier
    private long id;
    private String name;
    private int age;
    private String address;

    public UpdateUserCommand(long id, String name, int age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
}
