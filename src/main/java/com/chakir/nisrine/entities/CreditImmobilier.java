package com.chakir.nisrine.entities;

import com.chakir.nisrine.enums.BienType;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CI")
public class CreditImmobilier extends Credit{
    @Column(name="bien_type")
    @Enumerated(EnumType.STRING)
    private BienType bienType;
}
