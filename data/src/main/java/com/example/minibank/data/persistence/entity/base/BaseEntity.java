package com.example.minibank.data.persistence.entity.base;

import com.example.minibank.commons.constant.GenericStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.util.Date;

import static com.example.minibank.commons.constant.GenericStatus.ACTIVE;
import static javax.persistence.EnumType.STRING;

@Data
@MappedSuperclass
public class BaseEntity {
    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private Date modifiedDate;

    @Enumerated(STRING)
    @Column(name = "status")
    private GenericStatus status;

    public void setStatus(GenericStatus status) {
        this.status = status != null ? status : ACTIVE;
    }
}
