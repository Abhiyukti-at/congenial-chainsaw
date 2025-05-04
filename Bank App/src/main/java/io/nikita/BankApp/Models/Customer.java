package io.nikita.BankApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "pwd")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;

    @Column(name = "role")
    private String role;

    @Column(name = "create_dt")
    private Date createDt;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Authority> authorities;

}