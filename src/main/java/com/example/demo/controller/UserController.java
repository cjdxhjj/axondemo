package com.example.demo.controller;

import com.example.demo.domain.shared.entity.ActionResult;
import com.example.demo.domain.user.command.CreateUserCommand;
import com.example.demo.domain.user.command.UpdateUserCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/user")
public class UserController {
    private CommandGateway commandGateway;

    public UserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ActionResult<Boolean> createUser(@RequestBody CreateUserCommand cmd) {
        commandGateway.send(cmd);
        return new ActionResult(true);
    }

    @PutMapping
    public CompletableFuture<ActionResult<Boolean>> updateUser(@RequestBody UpdateUserCommand cmd) {
        return commandGateway.send(cmd);
    }
}
