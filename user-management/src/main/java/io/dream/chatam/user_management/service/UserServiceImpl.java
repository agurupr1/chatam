package io.dream.chatam.user_management.service;

import io.dream.chatam.user_management.model.CreateUserRequest;
import io.dream.chatam.user_management.model.UserMessage;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public UserMessage createUser(CreateUserRequest createUserRequest) {
        return null;
    }

    @Override
    public UserMessage getUser(UUID id) {
        return null;
    }

    @Override
    public UserMessage deleteUser(UUID id) {
        return null;
    }

    @Override
    public UserMessage deactivateUser(UUID id) {
        return null;
    }

    @Override
    public UserMessage activateUser(UUID id) {
        return null;
    }
}
