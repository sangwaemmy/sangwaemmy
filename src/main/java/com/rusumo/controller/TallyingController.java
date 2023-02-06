 
package com.rusumo.controller;

import com.rusumo.exception.ResourceNotFoundException;
import com.rusumo.models.Mdl_tallying;
import com.rusumo.DTO.MultipleTallyings;
import com.rusumo.repository.TallyingRepository;import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/rusumo_warehouses/api/tallying")
public class TallyingController {

    @Autowired
    TallyingRepository tallyingRepository;

    @ApiOperation("Getting all the Tallying only")
    @GetMapping("/")
    public  ResponseEntity<List<Mdl_tallying>> getAll() {
        List<Mdl_tallying> struc = new ArrayList<>();
        tallyingRepository.findAll().forEach(struc::add);
        if (struc.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(struc, HttpStatus.OK);
    }
    @ApiOperation("Creating a structure")
    @PostMapping("/")
    public ResponseEntity<Mdl_tallying> createStructure(@RequestBody @Valid Mdl_tallying mdl_tallying) {
        return new ResponseEntity<>(tallyingRepository.save(mdl_tallying), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Updating  a single Structure")
    public ResponseEntity<Mdl_tallying> updateStructure(@PathVariable(value = "id") long id, @RequestBody Mdl_tallying mdl_tallying) {
        Mdl_tallying mdl_tallying1 = tallyingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Structure not found"));
        mdl_tallying1.setTallying_id(mdl_tallying.getTallying_id());
        mdl_tallying1.setItem_name(mdl_tallying.getItem_name());
        mdl_tallying1.setQuantity(mdl_tallying.getQuantity());
        mdl_tallying1.setWeight(mdl_tallying.getWeight());
        mdl_tallying1.setDate_time(mdl_tallying.getDate_time());
        mdl_tallying1.setAccount(mdl_tallying.getAccount());
        mdl_tallying1.setArrival_id(mdl_tallying.getArrival_id());
        mdl_tallying1.setDescription(mdl_tallying.getDescription());
        return new ResponseEntity<>(tallyingRepository.save(mdl_tallying), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTallying(@PathVariable("id") long id) {
        tallyingRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    Adding more items at the same time
    @PostMapping("/multitallying")
    public ResponseEntity<String> multipletallyings(@RequestBody MultipleTallyings multipleTallyings) {
        List<Mdl_tallying> tallyingsList = multipleTallyings.getMultiTallyings();
        try {
            for (Mdl_tallying mdl_tallying : tallyingsList) {
                tallyingRepository.save(mdl_tallying);
            }
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Saved", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error "  + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
