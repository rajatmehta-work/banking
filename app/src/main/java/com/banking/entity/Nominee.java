package com.banking.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Nominee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nomineeId;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

    private String firstName;
    private String lastName;
    private String relationship;
    private String contactNumber;
    private String email;
    private LocalDateTime createdAt;

 
}
