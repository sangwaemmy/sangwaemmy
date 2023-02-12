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
@Table(name = "tallying")
@Getter @Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_tallying implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 20, message = " item_name should not be empty, null and or length exceed 30")
    @Column(name = "item_name", length = 20, nullable = false)
    private String item_name;

    @Size(min = 1, max = 20, message = " quantity should not be empty, null and or length exceed 30")
    @Column(name = "quantity", length = 20, nullable = false)
    private String quantity;

    @Size(min = 1, max = 20, message = " weight should not be empty, null and or length exceed 30")
    @Column(name = "weight", length = 20, nullable = false)
    private String weight;

    @Size(min = 1, max = 20, message = " date_time should not be empty, null and or length exceed 30")
    @Column(name = "date_time", length = 20, nullable = false)
    private String date_time;

    @Size(min = 1, max = 20, message = " description should not be empty, null and or length exceed 30")
    @Column(name = "description", length = 20, nullable = false)
    private String description;

    @Size(min = 1, max = 100, message = " account_id should not be empty, null and or length exceed 30")
    @Column(name = "account_id", length = 100, nullable = false)
    private String account_id;

 
    @ManyToOne
    @JoinColumn(name = "arriv_tallying")
    private Mdl_arrival mdl_arrival;
    

}
