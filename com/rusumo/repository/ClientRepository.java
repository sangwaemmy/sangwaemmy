package com.rusumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rusumo.models.Mdl_client;
@Repository
public interface ClientRepository extends JpaRepository<Mdl_client, Long> {
}

