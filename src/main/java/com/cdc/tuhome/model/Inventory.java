package com.cdc.tuhome.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "inventory")
public class Inventory implements Serializable {
    @Serial
    private static final long serialVersionUID = 2002156951289762702L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "propertyId", referencedColumnName = "id", nullable = false)
    private Properties property;
}
