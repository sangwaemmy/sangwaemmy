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
@Table(name = "receipt")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_receipt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 20, message = " date_time should not be empty, null and or length exceed 30")
    @Column(name = "date_time", length = 20, nullable = false)
    private String date_time;

    @Size(min = 1, max = 20, message = " invoice should not be empty, null and or length exceed 30")
    @Column(name = "invoice", length = 20, nullable = false)
    private Integer invoice;

    @Size(min = 1, max = 100, message = " account_id should not be empty, null and or length exceed 30")
    @Column(name = "account_id", length = 100, nullable = false)
    private String account_id;


        
    @ManyToOne
    @JoinColumn(name = "inv_receipt")
    private Mdl_invoice mdl_invoice;
}
