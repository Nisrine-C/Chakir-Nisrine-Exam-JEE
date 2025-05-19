package com.chakir.nisrine.entities;

import com.chakir.nisrine.enums.StatutType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE",length =4)
@Data @AllArgsConstructor @NoArgsConstructor
public abstract class Credit {
    @Id
    private Long id;
    @Column(name="date_demande")
    private Date dateDemande;
    @Enumerated(EnumType.STRING)
    private StatutType statut;
    @Column(name="date_acception")
    private Date dateAcception;
    private Double montant;
    @Column(name="duree_remboursement")
    private int dureeRemboursement;
    @Column(name="taux_interet")
    private float tauxInteret;

    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "credit",fetch=FetchType.EAGER)
    private List<Remboursement> remboursements;

}
