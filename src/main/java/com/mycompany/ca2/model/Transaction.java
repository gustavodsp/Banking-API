/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ca2.model;

/**
 *
 * @author gusta
 */
public class Transaction {
    
    private String description;
    private String date;
    private double postBalance;
    
    public Transaction(){
        
    }
    
    public Transaction(String description, String date, double postBalance) {
        this.description = description;
        this.date = date;
        this.postBalance = postBalance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPostBalance() {
        return postBalance;
    }

    public void setPostBalance(double postBalance) {
        this.postBalance = postBalance;
    }

    
    
}
