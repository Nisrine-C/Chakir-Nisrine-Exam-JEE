package com.chakir.nisrine.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CPR")
public class CreditProfessionel extends Credit{
    private String motif;
    @Column(name="raison_sociale")
    private String raisonSociale;
}
