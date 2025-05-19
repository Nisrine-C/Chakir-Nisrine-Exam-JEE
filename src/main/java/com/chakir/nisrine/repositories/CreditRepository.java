package com.chakir.nisrine.repositories;

import com.chakir.nisrine.entities.Client;
import com.chakir.nisrine.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit,Long> {
    List<Credit> findByClient(Client client);
}
