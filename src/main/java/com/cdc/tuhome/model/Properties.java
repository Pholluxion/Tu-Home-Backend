package com.cdc.tuhome.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "real_properties")
public class Properties implements Serializable {
    @Serial
    private static final long serialVersionUID = 8383816888758961643L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

//    TODO: fix to make a dropdown menu
    @Column(name = "propertyType", nullable = false)
    private String propertyType;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "availabilityDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date availabilityDate = new Date();

    @Column(name = "availabilityStatus", nullable = false)
    private Boolean availabilityStatus;

}
