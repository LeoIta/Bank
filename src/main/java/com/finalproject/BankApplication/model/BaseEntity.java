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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            nullable = false
    )
    Date created_at;


    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
            updatable = false,
            nullable = false
    )
    Date modified_at;
}