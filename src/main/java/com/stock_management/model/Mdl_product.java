package com.stock_management.model;

public class Mdl_product {

    private int product_id;
    private String name;
    private int quantity;
    private int unit_cost;
    private int sale_cost;
    private float total;

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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

}
