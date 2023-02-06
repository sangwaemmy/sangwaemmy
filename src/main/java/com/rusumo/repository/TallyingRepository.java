package com.rusumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rusumo.models.Mdl_tallying;
@Repository
public interface TallyingRepository extends JpaRepository<Mdl_tallying, Long> {
}

