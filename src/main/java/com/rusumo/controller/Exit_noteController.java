 
package com.rusumo.controller;

import com.rusumo.exception.ResourceNotFoundException;
import com.rusumo.models.Mdl_exit_note;
import com.rusumo.DTO.MultipleExit_notes;
import com.rusumo.repository.Exit_noteRepository;import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/rusumo_warehouses/api/exit_note")
public class Exit_noteController {

    @Autowired
    Exit_noteRepository exit_noteRepository;

    @ApiOperation("Getting all the Exit_note only")
    @GetMapping("/")
    public  ResponseEntity<List<Mdl_exit_note>> getAll() {
        List<Mdl_exit_note> struc = new ArrayList<>();
        exit_noteRepository.findAll().forEach(struc::add);
        if (struc.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(struc, HttpStatus.OK);
    }
    @ApiOperation("Creating a structure")
    @PostMapping("/")
    public ResponseEntity<Mdl_exit_note> createStructure(@RequestBody @Valid Mdl_exit_note mdl_exit_note) {
        return new ResponseEntity<>(exit_noteRepository.save(mdl_exit_note), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Updating  a single Structure")
    public ResponseEntity<Mdl_exit_note> updateStructure(@PathVariable(value = "id") long id, @RequestBody Mdl_exit_note mdl_exit_note) {
        Mdl_exit_note mdl_exit_note1 = exit_noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Structure not found"));
        mdl_exit_note1.setExit_note_id(mdl_exit_note.getExit_note_id());
        mdl_exit_note1.setReceipt(mdl_exit_note.getReceipt());
        return new ResponseEntity<>(exit_noteRepository.save(mdl_exit_note), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteExit_note(@PathVariable("id") long id) {
        exit_noteRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    Adding more items at the same time
    @PostMapping("/multiexit_note")
    public ResponseEntity<String> multipleexit_notes(@RequestBody MultipleExit_notes multipleExit_notes) {
        List<Mdl_exit_note> exit_notesList = multipleExit_notes.getMultiExit_notes();
        try {
            for (Mdl_exit_note mdl_exit_note : exit_notesList) {
                exit_noteRepository.save(mdl_exit_note);
            }
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Saved", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error "  + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
