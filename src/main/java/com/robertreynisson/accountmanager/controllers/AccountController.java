package com.robertreynisson.accountmanager.controllers;

import com.robertreynisson.accountmanager.controllers.domain.UserAccount;
import com.robertreynisson.accountmanager.controllers.domain.UserAccountAccountCreate;
import com.robertreynisson.accountmanager.service.AccountService;
import com.robertreynisson.accountmanager.service.domain.UserAccountException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class AccountController {


    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Returns all User Accounts")
    @SecurityRequirement(name = "basicAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserAccount.class)))),
            @ApiResponse(responseCode = "401", description = "Not authenticated", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @GetMapping("/")
    public List<UserAccount> GetAll() throws UserAccountException.APIError {
        return accountService.loadAllUsers();
    }


    @Operation(summary = "Returns a User Account by its id")
    @SecurityRequirement(name = "basicAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Account was created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserAccount.class))}),
            @ApiResponse(responseCode = "401", description = "Not authenticated", content = @Content),
            @ApiResponse(responseCode = "404", description = "User Account not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @GetMapping("/{id}")
    public UserAccount GetById(@PathVariable(value = "id") Long id) throws UserAccountException.APIError {
        return accountService.loadUserById(id);
    }

    @Operation(summary = "Creates a new User Account")
    @SecurityRequirement(name = "basicAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserAccount.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request (parameters missing or invalid)", content = @Content),
            @ApiResponse(responseCode = "401", description = "Not authenticated", content = @Content),
            @ApiResponse(responseCode = "403", description = "Login authenticated but not authorized", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public UserAccount Post(@RequestBody UserAccountAccountCreate userAccountCreate) throws UserAccountException.APIError {
        return accountService.createAccount(userAccountCreate);
    }


    @Operation(summary = "Updates an existing User Account")
    @SecurityRequirement(name = "basicAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserAccount.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request (parameters missing or invalid)", content = @Content),
            @ApiResponse(responseCode = "401", description = "Not authenticated", content = @Content),
            @ApiResponse(responseCode = "403", description = "Login authenticated but not authorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "User Account not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})

    @PutMapping("/")
    public UserAccount Put(@RequestBody UserAccountAccountCreate userAccountAccountCreate) throws UserAccountException.APIError {
        return accountService.updateUser(userAccountAccountCreate);
    }

    @Operation(summary = "Deletes a User Account")
    @SecurityRequirement(name = "basicAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserAccount.class))}),
            @ApiResponse(responseCode = "401", description = "Not authenticated", content = @Content),
            @ApiResponse(responseCode = "403", description = "Login authenticated but not authorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "User Account not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @DeleteMapping("/{id}")
    public void Delete(@PathVariable(value = "id") Long id) throws UserAccountException.APIError {
        accountService.deleteUser(id);
    }

}
