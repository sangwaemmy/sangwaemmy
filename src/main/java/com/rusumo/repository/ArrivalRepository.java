package com.rusumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rusumo.models.Mdl_arrival;
@Repository
public interface ArrivalRepository extends JpaRepository<Mdl_arrival, Long> {
}

