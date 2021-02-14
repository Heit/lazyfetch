package ru.kosinov.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kosinov.blog.dto.Authority;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}
