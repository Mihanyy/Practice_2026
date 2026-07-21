package com.example.bb.security;

import com.example.bb.user.UserRepository;
import com.example.bb.user.model.User;
import com.example.bb.user.model.UserState;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login){
        String normLogin = login.trim().toLowerCase(Locale.ROOT);

        User user = userRepository.findUserByLogin(normLogin).orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getLogin())
                .password(user.getPasswordHash())
                .roles(user.getRole().name())
                .disabled(user.getState() == UserState.BLOCKED)
                .build();
    }

}
