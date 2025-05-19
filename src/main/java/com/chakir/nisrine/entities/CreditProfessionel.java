package com.chakir.nisrine.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CPR")
@Data @NoArgsConstructor @AllArgsConstructor
public class CreditProfessionel extends Credit{
    private String motif;
    @Column(name="raison_sociale")
    private String raisonSociale;
}
