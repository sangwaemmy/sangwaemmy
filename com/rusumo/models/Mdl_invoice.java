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
@Table(name = "invoice")
@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_invoice implements Serializable {

 @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  
    @Size(min = 1, max = 20, message = " invoice_id should not be empty, null and or length exceed 30")
    @Column(name = "invoice_id", length = 20, nullable = false)
    private Integer invoice_id;
  
    @Size(min = 1, max = 20, message = " date_time should not be empty, null and or length exceed 30")
    @Column(name = "date_time", length = 20, nullable = false)
    private String date_time;

    public Mdl_invoice() {
    }

}
