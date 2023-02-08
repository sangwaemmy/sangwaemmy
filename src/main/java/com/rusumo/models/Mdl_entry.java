/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "entry")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mdl_entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Size(min = 1, max = 20, message = " date_time should not be empty, null and or length exceed 30")
    @Column(name = "date_time", length = 20, nullable = false)
    private String date_time;

    @Size(min = 1, max = 20, message = " year should not be empty, null and or length exceed 30")
    @Column(name = "year", length = 20, nullable = false)
    private String year;

    @Size(min = 1, max = 20, message = " plate_no should not be empty, null and or length exceed 30")
    @Column(name = "plate_no", length = 20, nullable = false)
    private String plate_no;

    @Size(min = 1, max = 20, message = " ddcom should not be empty, null and or length exceed 30")
    @Column(name = "ddcom", length = 20, nullable = false)
    private String ddcom;

    @Size(min = 1, max = 20, message = " country should not be empty, null and or length exceed 30")
    @Column(name = "country", length = 20, nullable = false)
    private String country;

    @Size(min = 1, max = 20, message = " status should not be empty, null and or length exceed 30")
    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @Size(min = 1, max = 20, message = " stat_paid should not be empty, null and or length exceed 30")
    @Column(name = "stat_paid", length = 20, nullable = false)
    private String stat_paid;

    @Size(min = 1, max = 20, message = " stat_del should not be empty, null and or length exceed 30")
    @Column(name = "stat_del", length = 20, nullable = false)
    private String stat_del;

    @Size(min = 1, max = 20, message = " descripiion should not be empty, null and or length exceed 30")
    @Column(name = "descripiion", length = 20, nullable = false)
    private String descripiion;

    @Size(min = 1, max = 100, message = " account_id should not be empty, null and or length exceed  100")
    @Column(name = "account_id", length = 100, nullable = false)
    private String account_id;

    @ManyToOne
    @JoinColumn(name = "client_entry")
    private Mdl_client mdl_client;
    
           
    
}
