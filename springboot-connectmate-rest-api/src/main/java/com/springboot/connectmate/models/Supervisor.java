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
        name = "supervisors",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"email"})
        }
)
public class Supervisor {
    // Primary Key
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    // Personal Data
    @Column(name = "name", nullable = true)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "phone", nullable = true)
    private String phone;
    @Column(name = "address", nullable = true)
    private String address;
    // Amazon Connect Data
    @Column(name = "instance_id", nullable = false)
    private String instanceId;
}
