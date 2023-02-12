package com.rusumo.controller;

import com.rusumo.exception.ResourceNotFoundException;
import com.rusumo.models.Mdl_arrival;
import com.rusumo.DTO.MultipleArrivals;
import com.rusumo.models.Mdl_client;
import com.rusumo.models.Mdl_entry;
import com.rusumo.models.Mdl_tallying;
import com.rusumo.repository.ArrivalRepository;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@RestController
@RequestMapping("/rusumo_warehouses/api/arrival")
@CrossOrigin("*")
public class ArrivalController {

    @Autowired
    ArrivalRepository arrivalRepository;

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    ClientRepository clientRepository;

    @ApiOperation("Getting all the Arrival only")
    @GetMapping("/")
    public ResponseEntity<List<Mdl_arrival>> getAll() {
        List<Mdl_arrival> struc = new ArrayList<>();
        arrivalRepository.findAll().forEach(struc::add);
        if (struc.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(struc, HttpStatus.OK);
    }

    @ApiOperation("Get arrival notice by id")
    @GetMapping("/{arrivalId}")
    public ResponseEntity<Mdl_arrival> getAll(@PathVariable(value = "arrivalId") long arrivalId) {
        if (!arrivalRepository.existsById(arrivalId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Mdl_arrival arrival = arrivalRepository.findById(arrivalId).orElseThrow(() -> new ResourceAccessException("Id " + arrivalId + " Not found"));
        return new ResponseEntity<>(arrival, HttpStatus.OK);
    }

    

    @ApiOperation("Creating an arrival notice")
    @PostMapping("/{clientId}/{entryId}")
    public ResponseEntity<Mdl_arrival> createStructure(@RequestBody
            @Valid Mdl_arrival mdl_arrival, @PathVariable(value = "clientId") Long clientId, @PathVariable(value = "entryId") long entryId) {
        Mdl_client mdl_client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceAccessException("Client with Id Not found: " + clientId));
        Mdl_entry mdl_entry = entryRepository.findById(entryId).orElseThrow(() -> new ResourceAccessException(" Entry no Not found"));
//        mdl_arrival.setMdl_client(mdl_client);
        mdl_arrival.setMdl_entry(mdl_entry);
        return new ResponseEntity<>(arrivalRepository.save(mdl_arrival), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/{entry_no}/{client_no}")
    @ApiOperation(value = "Updating  a single Structure")
    public ResponseEntity<Mdl_arrival> updateStructure(@PathVariable(value = "id") long id,
            @PathVariable(value = "entry_no") long entry_no, @PathVariable(value = "client_no") long client_no,
            @RequestBody Mdl_arrival mdl_arrival) {
        //find the entryNo
        Mdl_entry mdl_entry = entryRepository.findById(entry_no).orElseThrow(() -> new ResourceAccessException("Entry Not found"));
        Mdl_client mdl_client = clientRepository.findById(client_no).orElseThrow(() -> new ResourceAccessException("Client Not found"));
        Mdl_arrival mdl_arrival1 = arrivalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Arrival not found"));
        mdl_arrival1.setId(mdl_arrival.getId());
        mdl_arrival1.setMdl_entry(mdl_entry);
//        mdl_arrival1.setMdl_client(mdl_client);
        return new ResponseEntity<>(arrivalRepository.save(mdl_arrival), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteArrival(@PathVariable("id") long id) {
        arrivalRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    Adding more items at the same time
    @PostMapping("/multiarrival")
    public ResponseEntity<String> multiplearrivals(@RequestBody MultipleArrivals multipleArrivals
    ) {
        List<Mdl_arrival> arrivalsList = multipleArrivals.getMultiArrivals();
        try {
            for (Mdl_arrival mdl_arrival : arrivalsList) {
                arrivalRepository.save(mdl_arrival);
            }
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Saved", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
