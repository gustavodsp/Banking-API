/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ca2.model;

import java.util.ArrayList;

/**
 *
 * @author gusta
 */
public class Account {
    
    private String name;
    private String branch;
    private int accNumber;
    private double balance;
    private ArrayList<Transaction> transactions;
    
    public Account(){
        
    }

    public Account(String branch, String type) {
        this.name = type+"-"+((int) (Math.random()*1000));
        this.branch = branch;
        this.accNumber = (int) (Math.random()*100000000);
        this.balance=0.0;
        this.transactions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
    
}
