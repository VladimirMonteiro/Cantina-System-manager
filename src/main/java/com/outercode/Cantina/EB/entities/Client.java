package com.outercode.Cantina.EB.entities;

import com.outercode.Cantina.EB.entities.enums.Company;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_client")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String warName;

    private Integer soldierNumber;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Company company;

    @OneToMany(mappedBy = "client")
    private Set<Order> orders = new HashSet<>();

    public Client(Long id, String warName, Integer soldierNumber, String phone, Company company) {
        this.id = id;
        this.warName = warName;
        this.soldierNumber = soldierNumber;
        this.phone = phone;
        this.company = company;
    }
}
