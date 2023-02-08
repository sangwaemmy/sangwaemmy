package com.rusumo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "arrival")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_arrival implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "entry_arrival")
    Mdl_entry mdl_entry;//here it should be one but i dont know how to make it, one arrival is one entry actually

    @ManyToOne
    @JoinColumn(name = "client_arriv")
    private Mdl_client mdl_client;

}
