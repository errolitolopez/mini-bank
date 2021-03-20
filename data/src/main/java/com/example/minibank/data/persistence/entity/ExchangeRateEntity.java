package com.example.minibank.data.persistence.entity;

import com.example.minibank.data.persistence.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Table(name = "exchange_rate_tbl")
@Entity(name = "ExchangeRate")
@EqualsAndHashCode(callSuper = true)
public class ExchangeRateEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "currency")
    private String currency;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "description")
    private String description;

    public void setDescription(String description) {
        this.description = StringUtils.isEmpty(description) ? this.currency : description;
    }
}
