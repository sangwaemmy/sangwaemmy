package com.stock_management.model;

public class Mdl_purchase {

    private int purchase_id;
    private int product;
    private int purchase_qty;
    private int current_qty;
    private int account;
    private int person;
    private String date;
    private float total;

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    //product
    private String name;
    private int quantity;
    private int unit_cost;
    private int sale_cost;
    private float p_total;

    public float getP_total() {
        return p_total;
    }

    public void setP_total(float p_total) {
        this.p_total = p_total;
    }
    //person
    private String p_name;
    private String surname;
    private int category;
    private String gender;
    private String phone_number;

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

    public int getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(int purchase_id) {
        this.purchase_id = purchase_id;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getPurchase_qty() {
        return purchase_qty;
    }

    public void setPurchase_qty(int purchase_qty) {
        this.purchase_qty = purchase_qty;
    }

    public int getCurrent_qty() {
        return current_qty;
    }

    public void setCurrent_qty(int current_qty) {
        this.current_qty = current_qty;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
