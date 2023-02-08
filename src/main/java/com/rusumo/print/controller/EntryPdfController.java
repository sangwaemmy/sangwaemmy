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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Controller
@RequestMapping("/export/pdf")
public class EntryPdfController extends Commons {

    @Autowired
    EntryRepository entryRepository;

    @ApiOperation(value = "Retrieve all Etnries")
    @GetMapping("/entry")
    public void getVehicle(HttpServletResponse response) throws DocumentException, IOException {

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Bondentree_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        List<Mdl_entry> allenties = new ArrayList<>();
        entryRepository.findAll().forEach(allenties::add);
        EntryPdfExporter exp = new EntryPdfExporter(allenties);
        exp.export(response);

    }
}
