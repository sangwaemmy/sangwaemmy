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
@Table(name = "exit_note")
@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_exit_note implements Serializable {

 @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  
    @Size(min = 1, max = 20, message = " exit_note_id should not be empty, null and or length exceed 30")
    @Column(name = "exit_note_id", length = 20, nullable = false)
    private Integer exit_note_id;
  
    @Size(min = 1, max = 20, message = " receipt should not be empty, null and or length exceed 30")
    @Column(name = "receipt", length = 20, nullable = false)
    private Integer receipt;

    public Mdl_exit_note() {
    }

}
