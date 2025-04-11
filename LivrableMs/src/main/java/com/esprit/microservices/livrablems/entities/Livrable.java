package com.esprit.microservices.livrablems.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Livrable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivrable;
    private String projectName;

    @Column(nullable = false, length = 100)
    private String title;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String description;

    private int completed_count; //Nombre de livrables complétés
    private int total_count; //Nombre total de livrables

    @Enumerated(EnumType.STRING)
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date due_date; //date d'écheance
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;



}
