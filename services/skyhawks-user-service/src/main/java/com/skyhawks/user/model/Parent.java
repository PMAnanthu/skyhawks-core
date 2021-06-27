package com.skyhawks.user.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Data
@Getter
@Setter
@Entity
@Table
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private String fatherName;
    private String motherName;
    @Column(name = "primaryContact", unique = true)
    private String primaryContact;
    private String secondaryContact;
    private String email;
    private String address;
}
