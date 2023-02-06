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
@Table(name = "rec_details")
@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_rec_details implements Serializable {

 @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  
    @Size(min = 1, max = 20, message = " rec_details_id should not be empty, null and or length exceed 30")
    @Column(name = "rec_details_id", length = 20, nullable = false)
    private Integer rec_details_id;
  
    @Size(min = 1, max = 20, message = " mod_pay should not be empty, null and or length exceed 30")
    @Column(name = "mod_pay", length = 20, nullable = false)
    private String mod_pay;
  
    @Size(min = 1, max = 20, message = " amt_paid should not be empty, null and or length exceed 30")
    @Column(name = "amt_paid", length = 20, nullable = false)
    private String amt_paid;
  
    @Size(min = 1, max = 20, message = " receipt_id should not be empty, null and or length exceed 30")
    @Column(name = "receipt_id", length = 20, nullable = false)
    private Integer receipt_id;
  
    @Size(min = 1, max = 20, message = " description should not be empty, null and or length exceed 30")
    @Column(name = "description", length = 20, nullable = false)
    private String description;

    public Mdl_rec_details() {
    }

}
