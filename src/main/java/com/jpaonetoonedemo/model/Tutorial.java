/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jpaonetoonedemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author SANGWA
 */
@Data
@Entity
@Table(name="tutorials")
public class Tutorial {
    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "tutorial_generator")
    private long id;
    private String title;
    private String description;
    private boolean publish;

    public Tutorial() {
    }

    public Tutorial(String title, String description, boolean publish) {
        this.title = title;
        this.description = description;
        this.publish = publish;
    }
    
}
