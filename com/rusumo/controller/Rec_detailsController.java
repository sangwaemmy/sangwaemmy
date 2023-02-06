 
package com.rusumo.controller;

import com.rusumo.exception.ResourceNotFoundException;
import com.rusumo.models.Mdl_rec_details;
import com.rusumo.DTO.MultipleRec_detailss;
import com.rusumo.repository.Rec_detailsRepository;import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/rusumo_warehouses/api/rec_details")
public class Rec_detailsController {

    @Autowired
    Rec_detailsRepository rec_detailsRepository;

    @ApiOperation("Getting all the Rec_details only")
    @GetMapping("/")
    public  ResponseEntity<List<Mdl_rec_details>> getAll() {
        List<Mdl_rec_details> struc = new ArrayList<>();
        rec_detailsRepository.findAll().forEach(struc::add);
        if (struc.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(struc, HttpStatus.OK);
    }
    @ApiOperation("Creating a structure")
    @PostMapping("/")
    public ResponseEntity<Mdl_rec_details> createStructure(@RequestBody @Valid Mdl_rec_details mdl_rec_details) {
        return new ResponseEntity<>(rec_detailsRepository.save(mdl_rec_details), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Updating  a single Structure")
    public ResponseEntity<Mdl_rec_details> updateStructure(@PathVariable(value = "id") long id, @RequestBody Mdl_rec_details mdl_rec_details) {
        Mdl_rec_details mdl_rec_details1 = rec_detailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Structure not found"));
        mdl_rec_details1.setRec_details_id(mdl_rec_details.getRec_details_id());
        mdl_rec_details1.setMod_pay(mdl_rec_details.getMod_pay());
        mdl_rec_details1.setAmt_paid(mdl_rec_details.getAmt_paid());
        mdl_rec_details1.setReceipt_id(mdl_rec_details.getReceipt_id());
        mdl_rec_details1.setDescription(mdl_rec_details.getDescription());
        return new ResponseEntity<>(rec_detailsRepository.save(mdl_rec_details), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRec_details(@PathVariable("id") long id) {
        rec_detailsRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    Adding more items at the same time
    @PostMapping("/multirec_details")
    public ResponseEntity<String> multiplerec_detailss(@RequestBody MultipleRec_detailss multipleRec_detailss) {
        List<Mdl_rec_details> rec_detailssList = multipleRec_detailss.getMultiRec_detailss();
        try {
            for (Mdl_rec_details mdl_rec_details : rec_detailssList) {
                rec_detailsRepository.save(mdl_rec_details);
            }
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Saved", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error "  + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
