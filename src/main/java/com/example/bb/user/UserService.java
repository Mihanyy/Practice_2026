package com.example.bb.user;

import com.example.bb.user.dto.UserRegRequest;
import com.example.bb.user.dto.UserResponse;
import com.example.bb.user.model.User;
import com.example.bb.user.model.UserRole;
import com.example.bb.user.UserRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.util.Locale;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse register(UserRegRequest req){
        String name = req.name().trim();
        String login = req.login().trim().toLowerCase(Locale.ROOT);
        String email = req.email().trim().toLowerCase(Locale.ROOT);

        if(userRepository.existsByLogin(login)){
            throw new UserExists(
                    "Пользователь с таким логином уже существует"
            );
        }

        if(userRepository.existsByEmail(email)){
            throw new UserExists(
                    "Пользователь с такой почтой уже существует"
            );
        }

        User newUser = new User();

        newUser.setName(name);
        newUser.setLogin(login);
        newUser.setEmail(email);
        newUser.setPasswordHash(passwordEncoder.encode(req.password()));
        newUser.setRole(UserRole.USER);
        newUser.setBlocked(false);

        User savingUser = userRepository.save(newUser);

        return new UserResponse(
                savingUser.getId(),
                savingUser.getName(),
                savingUser.getLogin(),
                savingUser.getEmail(),
                savingUser.getRole(),
                savingUser.isBlocked(),
                savingUser.getCreatedAt()
        );

    }

    public UserResponse getByLogin(String login) {
        String normLogin = login.trim().toLowerCase(Locale.ROOT);

        User user = userRepository.findUserByLogin(normLogin).orElseThrow(
                ()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Пользователь не найден"
                ));

        return  new UserResponse(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getEmail(),
                user.getRole(),
                user.isBlocked(),
                user.getCreatedAt()
        );

    }
}

