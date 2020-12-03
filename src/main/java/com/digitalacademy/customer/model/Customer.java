package com.digitalacademy.customer.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100, message = "Please fill in your firstname")
    private String first_name;

    @NotNull
    @Size(min = 1, max = 100, message = "Please fill in your lastname")
    private String last_name;

    @Email(message = "Enter a valid email")
    private String email;

    @NotNull
    private String phoneNo;

    private Integer age;

}
