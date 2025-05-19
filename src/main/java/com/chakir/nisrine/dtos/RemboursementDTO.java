package com.chakir.nisrine.dtos;

import com.chakir.nisrine.entities.Credit;
import com.chakir.nisrine.enums.RemboursementType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jdk.jfr.DataAmount;
import lombok.Data;

import java.util.Date;

@Data
public class RemboursementDTO extends CreditDTO{
    private Long id;
    private Date date;
    private Double montant;
    private RemboursementType type;
    private CreditDTO credit;
}
