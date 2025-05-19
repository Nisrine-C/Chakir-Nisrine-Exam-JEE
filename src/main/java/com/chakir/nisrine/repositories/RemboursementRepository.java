package com.chakir.nisrine.repositories;

import com.chakir.nisrine.entities.Credit;
import com.chakir.nisrine.entities.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface RemboursementRepository extends JpaRepository<Remboursement,Long> {
    List<Remboursement> findByCredit(Credit credit);
}
