package com.example.minibank.data.persistence.entity;

import com.example.minibank.data.persistence.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.example.minibank.commons.utils.IdGenerator.generateReferenceCode;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Table(name = "user_tbl")
@Entity(name = "User")
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "reference_no")
    private String referenceNo;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo != null ? referenceNo : "MB" + generateReferenceCode();
    }
}
