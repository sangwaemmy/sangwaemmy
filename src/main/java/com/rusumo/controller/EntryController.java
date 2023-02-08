/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.controller;

import com.rusumo.models.Mdl_client;
import com.rusumo.models.Mdl_entry;
import com.rusumo.repository.ClientRepository;
import com.rusumo.repository.EntryRepository;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@RestController
@RequestMapping("/rusumo_warehouses/api/entry")
@CrossOrigin("*")
public class EntryController {

    @Autowired
    EntryRepository entryRepository;
    @Autowired
    ClientRepository clientRepository;

//    @Autowired
//    EntryRepository entryRepository;
    @ApiOperation(value = "Retrieve all Entries")
    @PostMapping("/add/{clientId}")
    public ResponseEntity<Mdl_entry> createUser(@RequestBody @Valid Mdl_entry mdl_entry, @PathVariable(value = "clientId") long clientId) {
        Mdl_client mdl_client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceAccessException("Id Not found"));
        mdl_entry.setMdl_client(mdl_client);
        return new ResponseEntity<>(entryRepository.save(mdl_entry), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Create an Entriy")
    @GetMapping("*")
    ResponseEntity< List<Mdl_entry>> getentries() {
        List<Mdl_entry> allentries = new ArrayList<>();
        entryRepository.findAll().forEach(allentries::add);
        return new ResponseEntity<>(allentries, HttpStatus.OK);
    }

}
