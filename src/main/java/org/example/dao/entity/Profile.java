package org.example.dao.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;


import static java.lang.System.currentTimeMillis;

@NoArgsConstructor
@Data
@Entity
@Table(name = "profile")
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id" , referencedColumnName = "id")
    private Customer customer;
    @Column(name = "password")
    private String password;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Profile( Customer customer,  String password) {
        this.customer = customer;
        this.password = password;
        this.isActive = true;
        this.createdAt = new java.sql.Timestamp(System.currentTimeMillis());
    }
}
