package com.sda.OnlineShop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Integer userId;
    private String fullName;
    private String emailAddress;
    private String password;
    private String address;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)  // cascade all va salva o data cu user si toate dependintele userului (adica si shopping cart)
    private ShoppingCart shoppingCart;
}
