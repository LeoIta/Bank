package com.finalproject.BankApplication.model;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@MappedSuperclass
abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(insertable=false)
    private int id;

    @CreationTimestamp
    @Column(insertable=false)
    Date created_at;

    @UpdateTimestamp
    @Column(insertable=false)
    Date modified_at;
}