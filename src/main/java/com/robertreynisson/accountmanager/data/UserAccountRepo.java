package com.robertreynisson.accountmanager.data;

import com.robertreynisson.accountmanager.data.domain.UserAccountDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepo extends JpaRepository<UserAccountDAO, Long> {

    Optional<UserAccountDAO> findByUserName(String userName);
}
