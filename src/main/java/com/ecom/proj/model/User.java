package com.ecom.proj.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    @NotEmpty
    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @NotEmpty
    @Column(nullable = false, unique = true)
    @Email(message = "errors.invalid.email")
    private String email;

    // @NotEmpty
    // @Column(nullable = false)
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID") }, inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID") })

    private List<Role> roles;

    public User(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }
    public User() {
     
    }
}
