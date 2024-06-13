package com.banking.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.banking.dto.TransactionDTO;
import com.banking.enums.TransactionStatus;
import com.banking.service.TransactionService;

/**
 * REST controller for managing transactions.
 */
@RestController
@Validated
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * POST /transactions : Create a new transaction.
     *
     * @param req the TransactionDTO containing the transaction details
     * @return the ResponseEntity with status 201 (Created) and with body the transaction status,
     *         or with status 400 (Bad Request) if the transaction could not be processed, (will handle in Exception handler)
     */
    @PostMapping
    public ResponseEntity<TransactionStatus> createTransaction(@RequestBody @Valid TransactionDTO req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.transact(req));// We can also use reactive way of programming at scale.
    }
}
