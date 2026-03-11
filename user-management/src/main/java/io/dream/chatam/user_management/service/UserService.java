package io.dream.chatam.user_management.service;

import io.dream.chatam.user_management.model.CreateUserRequest;
import io.dream.chatam.user_management.model.UserMessage;

import java.util.UUID;

public interface UserService {


    UserMessage createUser(CreateUserRequest createUserRequest);

    UserMessage getUser(UUID id);

    UserMessage deleteUser(UUID id);

    UserMessage deactivateUser(UUID id);

    UserMessage activateUser(UUID id);
}
