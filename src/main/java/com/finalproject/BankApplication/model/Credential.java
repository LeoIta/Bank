package com.finalproject.BankApplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Credential extends BaseEntity{

    @Column
    private String bankId;

    @Column
    private String password;

    @OneToOne(targetEntity = Account.class)
    private Account account;

    /*@Enumerated(EnumType.STRING)
    private Role role;*/

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
