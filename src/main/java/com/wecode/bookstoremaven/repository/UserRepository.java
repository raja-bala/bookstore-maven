package com.wecode.bookstoremaven.repository;

import com.wecode.bookstoremaven.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
