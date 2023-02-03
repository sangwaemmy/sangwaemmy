/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jpaonetoonedemo.controller;

import com.jpaonetoonedemo.model.Tutorial;
import com.jpaonetoonedemo.repository.TutorialRepository;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SANGWA
 */
@RestController
@RequestMapping("/rusumo_app/api/tutorials")
public class TutorialController {
    
    @Autowired
    TutorialRepository tutorialRepository;
    @ApiOperation(value = "Get all Tutorials ")
    @GetMapping()
    public ResponseEntity<List<Tutorial>> getAllTutorials(){
        List<Tutorial> tuts= new ArrayList<>();
        tutorialRepository.findAll().forEach(tuts::add);
        return new ResponseEntity<>(tuts,HttpStatus.OK);
    }
}
