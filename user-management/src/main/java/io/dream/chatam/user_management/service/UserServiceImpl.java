package io.dream.chatam.user_management.service;

import io.dream.chatam.user_management.dao.User;
import io.dream.chatam.user_management.model.CreateUserRequest;
import io.dream.chatam.user_management.model.UserMessage;
import io.dream.chatam.user_management.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${create-user.exchange.name}")
    private String exchange;

    @Value("${create-user.routing.key}")
    private String routingKey;

    @Override
    public UserMessage createUser(CreateUserRequest createUserRequest) {
        LOGGER.info("Attempting to create user with email: {}", createUserRequest.getEmail());
        Optional<User> user = userRepository.findByEmailId(createUserRequest.getEmail().toLowerCase());
        if (user.isPresent()) {
            LOGGER.warn("User with email {} already exists", createUserRequest.getEmail());
            // throw saying user exists, try with another id
            throw new RuntimeException("User already exists");
        }
        String encodedPassword = encoder.encode(createUserRequest.getPassword());
        User newUser = new User(createUserRequest.getEmail(), encodedPassword);
        User createdUser = userRepository.save(newUser);
        LOGGER.info("User created successfully with userId: {}", createdUser.getUserId());

        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("userId", createdUser.getUserId());
        userDetails.put("userEmailId", createdUser.getEmailId());

        LOGGER.info("Sending message to RabbitMQ");
        rabbitTemplate.convertAndSend(exchange, routingKey, userDetails);

        return new UserMessage("201", "User Created", userDetails);
    }

    @Override
    public UserMessage getUser(UUID id) {
        LOGGER.info("Fetching user with id: {}", id);
        return null;
    }

    @Override
    public UserMessage deleteUser(UUID id) {
        LOGGER.info("Deleting user with id: {}", id);
        return null;
    }

    @Override
    public UserMessage deactivateUser(UUID id) {
        LOGGER.info("Deactivating user with id: {}", id);
        return null;
    }

    @Override
    public UserMessage activateUser(UUID id) {
        LOGGER.info("Activating user with id: {}", id);
        return null;
    }
}
