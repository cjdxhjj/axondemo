package com.example.demo.domain.user.aggregate;


import com.example.demo.domain.shared.entity.ActionResult;
import com.example.demo.domain.user.command.CreateUserCommand;
import com.example.demo.domain.user.command.UpdateUserCommand;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.event.UserCreateEvent;
import com.example.demo.domain.user.event.UserUpdateEvent;
import com.example.demo.domain.user.repository.IUserRepository;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate(snapshotTriggerDefinition = "snapshotTriggerDefinition")
public class UserAggregate {
    @AggregateIdentifier
    private long id;
    private String name;
    private int age;
    private String address;

    public UserAggregate() {
    }

    @CommandHandler
    public UserAggregate(CreateUserCommand cmd, IUserRepository userRepository) {
        var user = userRepository.save(new User(0, cmd.getName(), cmd.getAge(), cmd.getAddress()));
        this.id = user.getId();
        this.name = cmd.getName();
        this.age = cmd.getAge();
        this.address = cmd.getAddress();
        AggregateLifecycle.apply(new UserCreateEvent(user.getId(), cmd.getName(), cmd.getAge(), cmd.getAddress()));
    }

    @CommandHandler
    public ActionResult<Boolean> on(UpdateUserCommand cmd, IUserRepository userRepository) {
        userRepository.save(new User(cmd.getId(), cmd.getName(), cmd.getAge(), cmd.getAddress()));
        this.name = cmd.getName();
        this.age = cmd.getAge();
        this.address = cmd.getAddress();
        AggregateLifecycle.apply(new UserUpdateEvent(cmd.getId(), cmd.getName(), cmd.getAge(), cmd.getAddress()));
        return new ActionResult<>(true);
    }

    @EventSourcingHandler
    public void on(UserCreateEvent event) {
        this.id = event.id();
        this.name = event.name();
        this.age = event.age();
        this.address = event.address();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
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
