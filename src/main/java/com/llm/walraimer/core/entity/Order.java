package com.llm.walraimer.core.entity;

import com.llm.walraimer.core.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor
@Getter
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_seq")
    @Setter
    private Long id;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Instant moment;

    private Integer orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @Setter
    private User client;

    @OneToMany(mappedBy = "id.order")
    private final Set<OrderItem> items = new HashSet<>();

    @Setter
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public Order(Long id, OrderStatus orderStatus, User client) {
        this.id = id;
        setOrderStatus(orderStatus);
        this.client = client;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public Double getTotal() {
        double sum = 0.0;
        for (OrderItem x : items) {
            sum += x.getSubTotal();
        }
        return sum;
    }
}
