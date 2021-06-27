/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.user.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Getter
@Setter
@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    @Column(name = "userId", unique = true)
    private String userId;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate dateOfBirth;
    private Integer gender;
    private Boolean onlyFemaleChild;
    private String address;
    private String email;
    private String admissionFor;
    private LocalDate dateOfAdmission;
    @Column(name = "admissionNumber", unique = true)
    private String admissionNumber;
    private UUID parent;
    private String castDetails;
    private String religion;
    private Integer bloodGroup;
}
