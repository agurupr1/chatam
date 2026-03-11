package io.dream.chatam.user_management.model;

import java.util.HashMap;
import java.util.Map;

public class UserMessage {
    private final String userCode;
    private final String userMessage;
    private final Map<String, String> userDetails = new HashMap<>();

    public UserMessage(String userCode, String userMessage, Map<String, String> userDetails) {
        this.userCode = userCode;
        this.userMessage = userMessage;
        this.userDetails.putAll(userDetails);
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "userCode='" + userCode + '\'' +
                ", userMessage='" + userMessage + '\'' +
                ", userDetails=" + userDetails +
                '}';
    }
}
