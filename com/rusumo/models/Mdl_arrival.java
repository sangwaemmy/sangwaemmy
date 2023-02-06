package com.rusumo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "arrival")
@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_arrival implements Serializable {

 @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  
    @Size(min = 1, max = 20, message = " arrival_id should not be empty, null and or length exceed 30")
    @Column(name = "arrival_id", length = 20, nullable = false)
    private Integer arrival_id;
  
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
  
    @Size(min = 1, max = 20, message = " client_id should not be empty, null and or length exceed 30")
    @Column(name = "client_id", length = 20, nullable = false)
    private Integer client_id;
  
    @Size(min = 1, max = 20, message = " account should not be empty, null and or length exceed 30")
    @Column(name = "account", length = 20, nullable = false)
    private Integer account;
  
    @Size(min = 1, max = 20, message = " stat_del should not be empty, null and or length exceed 30")
    @Column(name = "stat_del", length = 20, nullable = false)
    private String stat_del;
  
    @Size(min = 1, max = 20, message = " descripiion should not be empty, null and or length exceed 30")
    @Column(name = "descripiion", length = 20, nullable = false)
    private String descripiion;

    public Mdl_arrival() {
    }

}
