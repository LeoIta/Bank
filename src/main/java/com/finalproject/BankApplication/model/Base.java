package com.finalproject.BankApplication.model;
import javax.persistence.*;

@Entity
public class Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column()
    private int id;
}