package org.example.dao.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import static java.lang.System.currentTimeMillis;

@NoArgsConstructor
@Data
@Getter
@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "fin")
    private String fin;
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @OneToMany(mappedBy = "customer",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Card> cards;

    public Customer(String name, String surname, Date date, String fin) {
        this.isActive = true;
        this.name = name;
        this.surname = surname;
        this.birthDate = date.toLocalDate();
        this.fin = fin;
    }


}
