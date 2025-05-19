package com.chakir.nisrine.dtos;

import com.chakir.nisrine.entities.Client;
import com.chakir.nisrine.entities.Remboursement;
import com.chakir.nisrine.enums.StatutType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreditPersonnelDTO extends CreditDTO{
    private Long id;
    private Date dateDemande;
    private StatutType statut;
    private Date dateAcception;
    private Double montant;
    private int dureeRemboursement;
    private float tauxInteret;
    private String motif;
    private ClientDTO client;
}
