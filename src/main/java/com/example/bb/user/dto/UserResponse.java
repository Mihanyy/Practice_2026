package com.example.bb.user.dto;

import com.example.bb.user.model.UserRole;
import com.example.bb.user.model.UserState;
import java.time.OffsetDateTime;

public record UserResponse(
        Long id,
        String name,
        String login,
        String email,
        UserRole role,
        UserState state,
        OffsetDateTime createdAt
) {
}
