package com.springboot.connectmate.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods for us
@AllArgsConstructor // Lombok annotation to create a constructor with all the arguments
@NoArgsConstructor // Lombok annotation to create a constructor with no arguments

// User entity
@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "connect_id", nullable = false)
    private String connectId;

    @Column(name = "firebase_id", nullable = false)
    private String firebaseId;
}
