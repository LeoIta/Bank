package com.finalproject.BankApplication.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
/*@SequenceGenerator(
        name="ID_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="ID_SEQ", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)*/




@Table
public class Credential {
   /* @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="ID_SEQ_GEN"
    )
    @SequenceGenerator(
            name="ID_SEQ_GEN", //시퀀스 제너레이터 이름
            sequenceName="ID_SEQ", //시퀀스 이름
            initialValue=1, //시작값
            allocationSize=1 //메모리를 통해 할당할 범위 사이즈
    )
    private Integer id;*/


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String clientId;

    @Column
    private String passWord;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            nullable = false
    )
    private Date createDate;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    public Credential() {
    }

    public Credential(String clientId, String passWord) {
        this.clientId = clientId;
        this.passWord = passWord;
    }

    public int getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
