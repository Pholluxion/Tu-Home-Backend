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
@Table(name = "contracts")
public class Contracts implements Serializable {
    @Serial
    private static final long serialVersionUID = -5722409191109877599L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private Users userId;

    @ManyToOne
    @JoinColumn(name = "propertyId", referencedColumnName = "id", nullable = false)
    private Properties property;

    @Column(name = "startdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startdate = new Date();

    @Column(name = "enddate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date enddate = new Date();

    @Column(name = "rentalamount", nullable = false)
    private Double rentalamount;

    @Column(name = "includeservices", nullable = false)
    private Boolean includeservices;

    @Column(name = "includeadmin", nullable = false)
    private Boolean includeadmin;

    @Column(name = "serviceamount", nullable = true)
    private Double serviceamount;

    @Column(name = "admineamount", nullable = true)
    private Double admineamount;


}
