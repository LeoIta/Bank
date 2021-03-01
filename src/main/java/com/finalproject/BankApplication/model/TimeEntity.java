package com.finalproject.BankApplication.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class TimeEntity extends BaseEntity{

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            nullable = false
    )
    Date createdAt;


    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
            updatable = false,
            nullable = false
    )
    Date modifiedAt;

}