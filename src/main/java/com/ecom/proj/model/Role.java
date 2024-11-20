package com.ecom.proj.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer id;

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
