package com.cdc.tuhome.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "inventory_item")
public class InventoryItem implements Serializable {
    @Serial
    private static final long serialVersionUID = -5926393988321057043L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventoryId", referencedColumnName = "id", nullable = false)
    private Inventory inventory;

    @Column(name = "itemname", nullable = false)
    private String itemname;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "value", nullable = false)
    private Double value;
}
