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
public class Customer {
    
    private String name;
    private String address;
    private String email;
    private int password;
    private ArrayList<Account> accounts;
    
    public Customer(){
    
    }

    public Customer(String name, String address, String email, int password, String branch) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.accounts = new ArrayList<>();
        accounts.add(new Account(branch,"CURRENT"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
    
}
