package com.llm.walraimer.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_payment")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode
public class Payment {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant moment;

    @JsonIgnore
    @OneToOne
    @MapsId
    @Setter
    private Order order;

    public Payment(Order order) {
        this.order = order;
    }
}
