/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.print.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Data
public class Commons {

    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    String currentDateTime = dateFormatter.format(new Date());
}
