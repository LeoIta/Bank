package com.finalproject.BankApplication.model;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    @CreatedDate
    Date createdAt;

    @LastModifiedDate
    Date modifiedAt;
}