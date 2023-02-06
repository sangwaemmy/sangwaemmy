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
@Table(name = "profile")
@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_profile implements Serializable {

 @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  
    @Size(min = 1, max = 20, message = " profile_id should not be empty, null and or length exceed 30")
    @Column(name = "profile_id", length = 20, nullable = false)
    private Integer profile_id;
  
    @Size(min = 1, max = 20, message = " name should not be empty, null and or length exceed 30")
    @Column(name = "name", length = 20, nullable = false)
    private Integer name;
  
    @Size(min = 1, max = 20, message = " surname should not be empty, null and or length exceed 30")
    @Column(name = "surname", length = 20, nullable = false)
    private String surname;
  
    @Size(min = 1, max = 20, message = " date_birth should not be empty, null and or length exceed 30")
    @Column(name = "date_birth", length = 20, nullable = false)
    private Date date_birth;
  
    @Size(min = 1, max = 20, message = " gender should not be empty, null and or length exceed 30")
    @Column(name = "gender", length = 20, nullable = false)
    private String gender;
  
    @Size(min = 1, max = 20, message = " tel should not be empty, null and or length exceed 30")
    @Column(name = "tel", length = 20, nullable = false)
    private String tel;
  
    @Size(min = 1, max = 20, message = " email should not be empty, null and or length exceed 30")
    @Column(name = "email", length = 20, nullable = false)
    private String email;
  
    @Size(min = 1, max = 20, message = " residence should not be empty, null and or length exceed 30")
    @Column(name = "residence", length = 20, nullable = false)
    private String residence;
  
    @Size(min = 1, max = 20, message = " image should not be empty, null and or length exceed 30")
    @Column(name = "image", length = 20, nullable = false)
    private Integer image;

    public Mdl_profile() {
    }

}
