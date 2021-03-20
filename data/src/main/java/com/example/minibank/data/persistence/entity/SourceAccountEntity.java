package com.example.minibank.data.persistence.entity;

import com.example.minibank.commons.constant.SourceAccountType;
import com.example.minibank.data.persistence.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import static com.example.minibank.commons.utils.IdGenerator.generateAccountNumber;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Table(name = "source_account_tbl")
@Entity(name = "SourceAccount")
@EqualsAndHashCode(callSuper = true)
public class SourceAccountEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "user_reference_no")
    private String userReferenceNo;

    @Column(name = "account_number")
    private String accountNumber;

    @Enumerated(STRING)
    @Column(name = "type")
    private SourceAccountType type;

    @Column(name = "balance")
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber != null ? accountNumber : generateAccountNumber();
    }
}
