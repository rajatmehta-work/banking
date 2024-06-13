package com.banking.controller;

import com.banking.dto.AccountDTO;
import com.banking.enums.AccountStatus;
import com.banking.service.AccountService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing accounts.
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    // below comments can be replace with swagger comments
    /**
     * POST /accounts : Create a new account.
     *
     * @param accountDTO the account to create
     * @return the ResponseEntity with status 201 (Created) if the account is successfully created,
     * or status 409 (Conflict) if the account already exists.
     */
    @PostMapping
    public ResponseEntity<AccountStatus> createAccount(@RequestBody @Valid AccountDTO accountDTO) {
        AccountStatus status = accountService.createAccount(accountDTO);
        if(status != AccountStatus.FAILED){
            return ResponseEntity.status(HttpStatus.CREATED).body(status);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(status);
    }

    /**
     * GET /accounts/{id} : Get the account with the specified ID.
     *
     * @param id the ID of the account to retrieve
     * @return the ResponseEntity with status 200 (OK) and the account in the body,
     * or status 404 (Not Found) if the account does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id) {
        AccountDTO account = accountService.getAccount(id);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }
}
