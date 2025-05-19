package com.chakir.nisrine.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CP")
@Data @NoArgsConstructor @AllArgsConstructor
public class CreditPersonnel  extends Credit{
    private String motif;
}
