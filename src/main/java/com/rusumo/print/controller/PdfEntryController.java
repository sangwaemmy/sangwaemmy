/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.print.controller;

import com.lowagie.text.DocumentException;
import com.rusumo.models.Mdl_entry;
import com.rusumo.print.EntryPdfExporter;
import com.rusumo.repository.EntryRepository;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.ResourceAccessException;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Controller
@RequestMapping("/export/pdf")
public class PdfEntryController extends Commons {

    @Autowired
    EntryRepository entryRepository;

    @ApiOperation(value = "Retrieve all Etnries")
    @GetMapping("/entry/{entryId}")
    public void getVehicle(HttpServletResponse response, @PathVariable(value = "entryId") long clientid) throws DocumentException, IOException {

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Bondentree_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        Mdl_entry entries =    entryRepository.findById(clientid) .orElseThrow(() -> new ResourceAccessException("Id Not found"));;
        
        EntryPdfExporter exp = new EntryPdfExporter(entries);
        exp.export(response);

    }
}
