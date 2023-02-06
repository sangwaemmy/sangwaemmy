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
@Table(name = "account")
@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_account implements Serializable {

 @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  
    @Size(min = 1, max = 20, message = " account_id should not be empty, null and or length exceed 30")
    @Column(name = "account_id", length = 20, nullable = false)
    private Integer account_id;
  
    @Size(min = 1, max = 20, message = " username should not be empty, null and or length exceed 30")
    @Column(name = "username", length = 20, nullable = false)
    private String username;
  
    @Size(min = 1, max = 20, message = " password should not be empty, null and or length exceed 30")
    @Column(name = "password", length = 20, nullable = false)
    private String password;
  
    @Size(min = 1, max = 20, message = " account_category should not be empty, null and or length exceed 30")
    @Column(name = "account_category", length = 20, nullable = false)
    private Integer account_category;
  
    @Size(min = 1, max = 20, message = " profile should not be empty, null and or length exceed 30")
    @Column(name = "profile", length = 20, nullable = false)
    private Integer profile;

    public Mdl_account() {
    }

}
