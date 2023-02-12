package com.rusumo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "arrival")
@Getter @Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mdl_arrival implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "entry_arrival")
    Mdl_entry mdl_entry;//here it should be one but i dont know how to make it, one arrival is one entry actually

    @OneToMany(mappedBy = "mdl_arrival")
    @JsonIgnoreProperties("mdl_arrival")
    private List<Mdl_tallying> o_tallyings;
    
    @OneToMany(mappedBy = "mdl_arrival")
    @JsonIgnoreProperties("mdl_arrival")
    private List<Mdl_invoice> o_invoices;
    
}
