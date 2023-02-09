package com.rusumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rusumo.models.Mdl_arrival;
import com.rusumo.models.Mdl_tallying;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ArrivalRepository extends JpaRepository<Mdl_arrival, Long> {

   
}
