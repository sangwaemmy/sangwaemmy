/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock_management.model;


/**
 *
 * @author Etienne
 */
public class Mdl_paydebts {

    private int paydebts_id;
    private int account;
    private int product;
    private int amt_paid;
    private int amt_remaining;
    private String date_paid;
    private int person;
    
     // person
    private String name;
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAmt_paid() {
        return amt_paid;
    }

    public void setAmt_paid(int amt_paid) {
        this.amt_paid = amt_paid;
    }

    public int getPaydebts_id() {
        return paydebts_id;
    }

    public void setPaydebts_id(int paydebts_id) {
        this.paydebts_id = paydebts_id;
    }

   

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getAmt_remaining() {
        return amt_remaining;
    }

    public void setAmt_remaining(int amt_remaining) {
        this.amt_remaining = amt_remaining;
    }

    public String getDate_paid() {
        return date_paid;
    }

    public void setDate_paid(String date_paid) {
        this.date_paid = date_paid;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

}
