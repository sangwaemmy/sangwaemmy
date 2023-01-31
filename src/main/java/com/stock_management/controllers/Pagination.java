/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock_management.controllers;
/**
 *
 * @author Admin
 */
public class Pagination {

    private int start;
    private int end;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;

        switch (start) {
            case 0:
                //no start clicked
                this.start = 0;
                this.end = 20;
                break;
            case 1:
                this.start = 0;
                this.end = 20;
                break;
            default:
                this.start = 20 * (start - 1);
                this.end =  20;
                break;
        }
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Pagination() {
    }
 
}
