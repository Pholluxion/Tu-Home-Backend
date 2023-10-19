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
@Table(name = "announcements")
public class Announcements implements Serializable{
    @Serial
    private static final long serialVersionUID = -6658343720444742823L;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "sendingDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date sendingDate = new Date();

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "senderId", referencedColumnName = "id", nullable = false)
    private Users sender;

    @ManyToOne
    @JoinColumn(name = "receiverId", referencedColumnName = "id", nullable = false)
    private Users receiver;

}
