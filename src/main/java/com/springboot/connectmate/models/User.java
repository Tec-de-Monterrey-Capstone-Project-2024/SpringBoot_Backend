package com.springboot.connectmate.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods for us
@AllArgsConstructor // Lombok annotation to create a constructor with all the arguments
@NoArgsConstructor // Lombok annotation to create a constructor with no arguments

@Entity
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint( columnNames = { "firebase_id", "connect_id" }, name = "uk_user_firebase_id_connect_id" ),
                @UniqueConstraint(columnNames = { "connect_id" }, name = "uk_connect_id")
        },
        indexes = {
                @Index(columnList = "connect_id", name = "idx_connect_id"),
        }
)
public class User {

    @Id
    @Column(name = "firebase_id", nullable = false)
    private String firebaseId;

    @Column(name = "connect_id", nullable = false)
    private String connectId;
}
