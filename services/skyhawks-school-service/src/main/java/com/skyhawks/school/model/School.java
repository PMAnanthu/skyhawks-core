package com.skyhawks.school.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table
@Getter
@Setter
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    @Column(name = "schoolCode", unique = true)
    private String schoolCode;
    private String name;
    private String email;
    private String webSite;
    private String address;
    private String education;
    private String mangerId;
    private boolean active;
}
