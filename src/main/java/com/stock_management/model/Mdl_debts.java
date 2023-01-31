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
public class Mdl_debts {

    private int debts_id;
    private int amount;
    private int account;
    private int product;
    private int total_debts;
    private String date_debts;
    private int person;

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }
// profile
    private  String name;
    private  String surname;

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
    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }
    

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDebts_id() {
        return debts_id;
    }

    public void setDebts_id(int debts_id) {
        this.debts_id = debts_id;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getDate_debts() {
        return date_debts;
    }

    public void setDate_debts(String date_debts) {
        this.date_debts = date_debts;
    }

    public int getTotal_debts() {
        return total_debts;
    }

    public void setTotal_debts(int total_debts) {
        this.total_debts = total_debts;
    }
}
