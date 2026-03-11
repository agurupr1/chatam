package io.dream.chatam.user_management.controller;

import io.dream.chatam.user_management.model.CreateUserRequest;
import io.dream.chatam.user_management.model.UserMessage;
import io.dream.chatam.user_management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/ums/user")
@Tag(name = "User API", description = "Endpoints for managing system users")
public class UserRestController {

    @Autowired
    UserService userService;

    @Operation(summary = "Create new user", description = "Create new user")
    @PostMapping("/")
    public UserMessage createUser(@RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @Operation(summary = "Get user by Id", description = "Get user by Id")
    @GetMapping("/{id}")
    public UserMessage getUser(@PathVariable UUID id) {
        return userService.getUser(id);
    }

    @Operation(summary = "Delete user by id", description = "Delete user by id")
    @PostMapping("/")
    public UserMessage deleteUser(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }

    @Operation(summary = "Deactivate user by id", description = "Deactivate user by id")
    @PostMapping("/")
    public UserMessage deactivateUser(@PathVariable UUID id) {
        return userService.deactivateUser(id);
    }

    @Operation(summary = "Activate user by id", description = "Activate user by id")
    @PostMapping("/")
    public UserMessage activateUser(@PathVariable UUID id) {
        return userService.activateUser(id);
    }
}
