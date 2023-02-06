 
package com.rusumo.controller;

import com.rusumo.exception.ResourceNotFoundException;
import com.rusumo.models.Mdl_arrival;
import com.rusumo.DTO.MultipleArrivals;
import com.rusumo.repository.ArrivalRepository;import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@RestController
@RequestMapping("/rusumo_warehouses/api/arrival")
public class ArrivalController {

    @Autowired
    ArrivalRepository arrivalRepository;

    @ApiOperation("Getting all the Arrival only")
    @GetMapping("/")
    public  ResponseEntity<List<Mdl_arrival>> getAll() {
        List<Mdl_arrival> struc = new ArrayList<>();
        arrivalRepository.findAll().forEach(struc::add);
        if (struc.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(struc, HttpStatus.OK);
    }
    @ApiOperation("Creating a structure")
    @PostMapping("/")
    public ResponseEntity<Mdl_arrival> createStructure(@RequestBody @Valid Mdl_arrival mdl_arrival) {
        return new ResponseEntity<>(arrivalRepository.save(mdl_arrival), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Updating  a single Structure")
    public ResponseEntity<Mdl_arrival> updateStructure(@PathVariable(value = "id") long id, @RequestBody Mdl_arrival mdl_arrival) {
        Mdl_arrival mdl_arrival1 = arrivalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Structure not found"));
        mdl_arrival1.setArrival_id(mdl_arrival.getArrival_id());
        mdl_arrival1.setDate_time(mdl_arrival.getDate_time());
        mdl_arrival1.setYear(mdl_arrival.getYear());
        mdl_arrival1.setPlate_no(mdl_arrival.getPlate_no());
        mdl_arrival1.setDdcom(mdl_arrival.getDdcom());
        mdl_arrival1.setCountry(mdl_arrival.getCountry());
        mdl_arrival1.setStatus(mdl_arrival.getStatus());
        mdl_arrival1.setStat_paid(mdl_arrival.getStat_paid());
        mdl_arrival1.setClient_id(mdl_arrival.getClient_id());
        mdl_arrival1.setAccount(mdl_arrival.getAccount());
        mdl_arrival1.setStat_del(mdl_arrival.getStat_del());
        mdl_arrival1.setDescripiion(mdl_arrival.getDescripiion());
        return new ResponseEntity<>(arrivalRepository.save(mdl_arrival), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteArrival(@PathVariable("id") long id) {
        arrivalRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    Adding more items at the same time
    @PostMapping("/multiarrival")
    public ResponseEntity<String> multiplearrivals(@RequestBody MultipleArrivals multipleArrivals) {
        List<Mdl_arrival> arrivalsList = multipleArrivals.getMultiArrivals();
        try {
            for (Mdl_arrival mdl_arrival : arrivalsList) {
                arrivalRepository.save(mdl_arrival);
            }
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Saved", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error "  + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
