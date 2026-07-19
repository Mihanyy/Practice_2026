package com.example.bb.user;

import com.example.bb.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByLogin (String login);

    Optional<User> findAllByLogin(String login);

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);


}
