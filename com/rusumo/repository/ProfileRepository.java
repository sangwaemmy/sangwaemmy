package com.rusumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rusumo.models.Mdl_profile;
@Repository
public interface ProfileRepository extends JpaRepository<Mdl_profile, Long> {
}

