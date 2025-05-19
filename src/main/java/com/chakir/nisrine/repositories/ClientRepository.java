package com.chakir.nisrine.repositories;

import com.chakir.nisrine.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    List<Client> findByNomContainsIgnoreCase(@Param("kw") String keyword);
}
