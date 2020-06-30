package com.robertreynisson.accountmanager.data;

import com.robertreynisson.accountmanager.data.domain.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserDAO, Long> {
}
