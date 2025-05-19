package com.chakir.nisrine.entities;

import com.chakir.nisrine.enums.BienType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CI")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditImmobilier extends Credit{
    @Column(name="bien_type")
    @Enumerated(EnumType.STRING)
    private BienType bienType;
}
