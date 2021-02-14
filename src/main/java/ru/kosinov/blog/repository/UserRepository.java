package ru.kosinov.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kosinov.blog.dto.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findOneByLogin(String login);
}
