package com.banking.controller;

import com.banking.dto.AccountDTO;
import com.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Boolean> createAccount(@RequestBody AccountDTO accountDTO) {
        if(accountService.createAccount(accountDTO)){
            ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id) {
        AccountDTO account = accountService.getAccount(id);
        if(account==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }
}
