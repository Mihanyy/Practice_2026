package com.example.bb.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegRequest(
        @NotBlank(message = "Имя не должно быть пустым")
        @Size(min = 3, max = 100, message = "Имя должно содержать не менее 3 и не более 100 символов")
        String name,

        @NotBlank(message = "Логин не должен быть пустым")
        @Size(min = 3, max = 50, message = "Логин должен содержать не менее 3 и не более 50 символов")
        String login,

        @NotBlank(message = "Email не должно быть пустым")
        @Email(message = "Некорректный email")
        String email,

        @NotBlank(message = "Пароль не должен быть пустым")
        @Size(min = 8, max = 100, message = "Пароль должен содержать не менее 8 и не более 100 символов")
        String password
) {
}
