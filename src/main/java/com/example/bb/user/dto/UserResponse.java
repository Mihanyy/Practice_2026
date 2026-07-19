package com.example.bb.user.dto;

import com.example.bb.user.model.UserRole;
import java.time.OffsetDateTime;

public record UserResponse(
        Long id,
        String name,
        String login,
        String email,
        UserRole role,
        boolean blocked,
        OffsetDateTime createdAt
) {
}
