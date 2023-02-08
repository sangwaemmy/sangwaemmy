/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rusumo.repository;

import com.rusumo.models.Mdl_entry;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author HP
 */
public interface EntryRepository extends PagingAndSortingRepository<Mdl_entry, Long> {
    
}
