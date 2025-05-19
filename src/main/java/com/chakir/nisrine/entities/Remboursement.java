package com.chakir.nisrine.entities;

import com.chakir.nisrine.enums.RemboursementType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Remboursement {
    @Id
    private Long id;
    private Date date;
    private Double montant;
    @Enumerated(EnumType.STRING)
    private RemboursementType type;

    @ManyToOne
    private Credit credit;

}
