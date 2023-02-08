/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Entity
@Table(name = "tariff")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mdl_tariff {

    @Id
    @Size(min = 1, max = 18, message = " id should not be empty, null and or lengt exceed 30")
    @Column(name = "id", nullable = false)
    private long id;

    @Size(min = 1, max = 18, message = " price_per_kg  should not be empty, null and or lengt exceed 30")
    @Column(name = "price_per_kg", nullable = false)
    private String price_per_kg;

    @Size(min = 1, max = 18, message = " max_days  should not be empty, null and or lengt exceed 30")
    @Column(name = "max_days", nullable = false)
    private String max_days;

}
