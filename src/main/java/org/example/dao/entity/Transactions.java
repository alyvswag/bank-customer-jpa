package org.example.dao.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.Ccy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transactions implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "receiver")
    private String receiver;
    @Column(name = "ccy")
    @Enumerated(EnumType.STRING)
    private Ccy ccy;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender", referencedColumnName = "id")
    private Card senderCard;

    public Transactions(String receiver, Card senderCard, Ccy ccy, BigDecimal amount) {
        this.receiver = receiver;
        this.senderCard = senderCard;
        this.ccy = ccy;
        this.amount = amount;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
}
