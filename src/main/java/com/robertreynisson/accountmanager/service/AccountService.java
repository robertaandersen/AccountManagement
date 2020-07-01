package com.robertreynisson.accountmanager.service;

import com.robertreynisson.accountmanager.controllers.domain.UserAccount;
import com.robertreynisson.accountmanager.controllers.domain.UserAccountAccountCreate;
import com.robertreynisson.accountmanager.data.UserAccountRepo;
import com.robertreynisson.accountmanager.data.domain.UserAccountDAO;
import com.robertreynisson.accountmanager.service.domain.AccountUserDetails;
import com.robertreynisson.accountmanager.service.domain.UserAccountException;
import com.robertreynisson.accountmanager.service.domain.UserAccountException.NotFound;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class AccountService implements UserDetailsService {

    private final UserAccountRepo userAccountRepo;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public AccountService(UserAccountRepo userAccountRepo) {
        this.userAccountRepo = userAccountRepo;
    }

    public UserAccount createAccount(UserAccountAccountCreate userAccountCreate) throws UserAccountException.APIError {
        validate(userAccountCreate);
        validateUserDoesNotExist(userAccountCreate);
        try {
            userAccountCreate.setPassword(bCryptPasswordEncoder.encode(userAccountCreate.getPassword()));
            UserAccountDAO userAccountDAO = new UserAccountDAO(userAccountCreate);
            userAccountRepo.save(userAccountDAO);

            return new UserAccount(userAccountDAO);
        } catch (Exception e) {
            throw new UserAccountException.InternalError();
        }
    }

    public List<UserAccount> loadAllUsers() {
        try {
            return userAccountRepo.findAll().stream().map(UserAccount::new).collect(Collectors.toList());
        } catch (Exception e) {
            throw new UserAccountException.InternalError();
        }
    }

    public UserAccount loadUserById(Long id) throws NotFound {
        try {
            return new UserAccount(loadAccountByIdOrThrow(id));
        } catch (Exception e) {
            if (e instanceof NotFound) {
                throw e;
            }
            throw new UserAccountException.InternalError();
        }
    }


    public void deleteUser(Long id) throws NotFound {
        try {
            userAccountRepo.delete(loadAccountByIdOrThrow(id));
        } catch (Exception e) {
            throw new UserAccountException.InternalError();
        }
    }

    public UserAccount updateUser(UserAccountAccountCreate userAccountAccountCreate) throws
            UserAccountException.APIError {
        validate(userAccountAccountCreate);
        UserAccountDAO userAccountDAO = loadAccountByIdOrThrow(userAccountAccountCreate.getId());
        if (!userAccountDAO.getUserName().equalsIgnoreCase(userAccountAccountCreate.getUserName())) {
            validateUserDoesNotExist(userAccountAccountCreate);
        }
        try {
            userAccountDAO.setFirstName(userAccountAccountCreate.getFirstName());
            userAccountDAO.setLastName(userAccountAccountCreate.getLastName());
            userAccountDAO.setUserName(userAccountAccountCreate.getUserName());
            userAccountDAO.setPassword(bCryptPasswordEncoder.encode(userAccountAccountCreate.getPassword()));
            userAccountDAO.setEmail(userAccountAccountCreate.getEmail());
            userAccountDAO.setPhone(userAccountAccountCreate.getPhone());
            userAccountDAO.setRole(String.valueOf(userAccountAccountCreate.getRole()));
            return new UserAccount(userAccountRepo.save(userAccountDAO));
        } catch (Exception e) {
            throw new UserAccountException.InternalError();
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userAccountRepo
                    .findByUserName(username)
                    .map(AccountUserDetails::new)
                    .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("User does not exist"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    private UserAccountDAO loadAccountByIdOrThrow(Long id) throws NotFound {
        Optional<UserAccountDAO> userAccount = userAccountRepo.findById(id);
        if (userAccount.isEmpty()) {
            throw new NotFound();
        }
        return userAccount.get();
    }

    private void validate(UserAccountAccountCreate userAccountCreate) {
        if (userAccountCreate.getPassword() == null || userAccountCreate.getPassword().isEmpty()) {
            throw new UserAccountException.BadRequest("Password missing");
        }
        if (userAccountCreate.getUserName() == null || userAccountCreate.getUserName().isEmpty()) {
            throw new UserAccountException.BadRequest("Username missing");
        }
    }

    private void validateUserDoesNotExist(UserAccountAccountCreate userAccountCreate) {
        if (userAccountRepo.findByUserName(userAccountCreate.getUserName()).isPresent()) {
            throw new UserAccountException.BadRequest("Username exists");
        }
    }
}
