package com.example.minibank.data.persistence.entity;

import com.example.minibank.commons.constant.TransactionStatus;
import com.example.minibank.commons.constant.TransactionType;
import com.example.minibank.commons.utils.IdGenerator;
import com.example.minibank.data.persistence.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Table(name = "transaction_tbl")
@Entity(name = "Transaction")
@EqualsAndHashCode(callSuper = true)
public class TransactionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "reference_no")
    private String referenceNo;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "currency")
    private String currency;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "remaining_balance")
    private BigDecimal remainingBalance;

    @Enumerated(STRING)
    @Column(name = "transaction_type")
    private TransactionType type;

    @CreationTimestamp
    @Column(name = "transaction_date")
    private Date date;

    @Enumerated(STRING)
    @Column(name = "transaction_status")
    private TransactionStatus transactionStatus;

    @Column(name = "free_text")
    private String freeText;

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo != null ? referenceNo : "TR" + IdGenerator.generateReferenceCode();
    }
}
