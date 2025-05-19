package com.chakir.nisrine.dtos;

import com.chakir.nisrine.enums.StatutType;
import lombok.Data;

import java.util.Date;

@Data
public class CreditProfessionelDTO {
    private Long id;
    private Date dateDemande;
    private StatutType statut;
    private Date dateAcception;
    private Double montant;
    private int dureeRemboursement;
    private float tauxInteret;
    private String motif;
    private String raisonSociale;
    private ClientDTO client;
}
