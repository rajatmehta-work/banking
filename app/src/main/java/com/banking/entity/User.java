// import javax.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "users")
// public class User {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(name = "phone")
//     private String phone;

//     @Column(name = "email", unique = true, nullable = false)
//     private String email;

//     @Column(name = "address")
//     private String address;

//     @Column(name = "state")
//     private String state;

//     @Column(name = "country")
//     private String country;

//     @Column(name = "district")
//     private String district;

//     @Column(name = "pan")
//     private String pan;

//     @Column(name = "adhar_card")
//     private String adharCard;

//     @Column(name = "name")
//     private String name;

//     @Column(name = "user_id", unique = true, nullable = false)
//     private String userId;

//     @Column(name = "created_at", nullable = false)
//     private LocalDateTime createdAt;

//     @Column(name = "updated_at", nullable = false)
//     private LocalDateTime updatedAt;

//     @Column(name = "pincode")
//     private String pincode;

//     @Enumerated(EnumType.STRING)
//     @Column(name = "type", nullable = false)
//     private UserType type;

//     // Getters and setters
// }
