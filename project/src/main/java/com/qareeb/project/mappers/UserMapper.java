package com.qareeb.project.mappers;

import com.qareeb.project.dto.UserResponse;
import com.qareeb.project.models.User;

public class UserMapper {

    public static UserResponse toDTO(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}