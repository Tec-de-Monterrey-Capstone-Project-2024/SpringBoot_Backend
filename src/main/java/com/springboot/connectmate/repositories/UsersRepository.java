package com.springboot.connectmate.repositories;

import com.springboot.connectmate.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {
}
