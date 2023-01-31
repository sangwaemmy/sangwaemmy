package com.stock_management.model;

public class Mdl_damages {

    private int damage_id;
    private int product;
    private int damage_qty;
    private int account;
    private String date;
    //pruduct
    private String name;
    private int quantity;
    private int unit_cost;
    private int sale_cost;
    //person
    private String p_name;
    private String surname;
    private int category;
    private String gender;
    private String phone_number;
    //acc_category

    public int getDamage_id() {
        return damage_id;
    }

    public void setDamage_id(int damage_id) {
        this.damage_id = damage_id;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getDamage_qty() {
        return damage_qty;
    }

    public void setDamage_qty(int damage_qty) {
        this.damage_qty = damage_qty;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(int unit_cost) {
        this.unit_cost = unit_cost;
    }

    public int getSale_cost() {
        return sale_cost;
    }

    public void setSale_cost(int sale_cost) {
        this.sale_cost = sale_cost;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
   

}
