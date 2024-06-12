package com.banking.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.banking.dto.TransactionDTO;
import com.banking.enums.TransactionStatus;
import com.banking.service.TransactionService;

@RestController
@Validated
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionStatus> createTransaction(@RequestBody TransactionDTO req) {
        return ResponseEntity.ok(transactionService.transact(req));
    }
}
