package com.finalproject.BankApplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

    @MappedSuperclass
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Table
    public abstract class PersonEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY )
        @Column(insertable=false)
        private int id;

        private String bankId;
        private String password;

        @Enumerated(EnumType.STRING)
        private Role role;

        private String firstName;
        private String lastName;

}
