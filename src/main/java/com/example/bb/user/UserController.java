package com.example.bb.user;

import com.example.bb.user.dto.UserRegRequest;
import com.example.bb.user.dto.UserResponse;
import com.example.bb.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(
            @Valid
            @RequestBody
            UserRegRequest req
    )
    {
        return userService.register(req);
    }

    @GetMapping("/profile")
    public UserResponse getCurrentUser(Authentication authentication) {
        return userService.getByLogin(
                authentication.getName()
        );
    }

}
