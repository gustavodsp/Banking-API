
package com.mycompany.ca2.service;

import com.mycompany.ca2.model.*;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author gusta
 */
public class CustomerService {
    
    public static ArrayList<Customer> customers = new ArrayList<>();
    
    
    public ArrayList<Customer> createExampleCustomers(){
        
        Customer c1 = new Customer("Gustavo Pereira", "2B Emmet Lodge", "gustavo@hotmail.com", 54321, "13MA");
        Customer c2 = new Customer("Gelcio Filho", "3 Dame Street", "gfilho@hotmail.com", 12345, "13MA");
        Customer c3 = new Customer("Jie Pu", "14 Summerhill", "jiepu@gmail.com", 13579, "06GE");
        
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
                
        return customers;
    }
    
    public ArrayList<Customer> getAllCustomers(){
        return customers;
    }
    
    public Customer createCustomer(Customer e){
        customers.add(e);
        Customer novo = searchCustomer(e.getEmail());
        return novo;
    }
    
//    public String deleteCustomer(int id){
//        if(id < customers.size()){
//            customers.remove(id);
//            return "Customer correctly deleted: " + id;
//        }
//        else{
//            return "Customer does not exist: " + id;
//        }
//    }
    
    public Customer searchCustomer(String email){
        
        Customer match = new Customer();
        
        for (Customer me : customers){
            if(me.getEmail().equals(email)){
                match=me;
            }
        }
        
        return match;
    }

    public String createAccount(String email, Account account){
        
        for (Customer me : customers){
            if(me.getEmail().equals(email)){
                ArrayList<Account> accounts = me.getAccounts();
                accounts.add(account);
                me.setAccounts(accounts);
            }
        }
        
        return "Account created successfully";
        
    }
    
    public Account getAcc(String branch, int accNumber){
        
        Account account = null;
        for (Customer me : customers){
                ArrayList<Account> accounts = me.getAccounts();
                for(Account acc : accounts){
                    if(acc.getBranch().equals(branch) && acc.getAccNumber()==accNumber){
                        account=acc;
                        return account;                        
                    }                    
            }
        }
        return account;
    }
    
    public String lodgement(double value, String branch, int accNumber){
        
        String response="Account not found";
        Account acc = this.getAcc(branch, accNumber);
        
        if(acc!=null){

            double postBalance = acc.getBalance()+value;
            acc.setBalance(postBalance);

            Date date = new Date();
            Transaction t = new Transaction("D-LM-"+value,date.toString(),postBalance);

            ArrayList<Transaction> transactions = acc.getTransactions();
            transactions.add(t);

            response = "Lodgement succeeded";

        }
                    
        return response;
    }
    
    public String withdrawal(double value, String branch, int accNumber){
        
        String response="";
        Account acc = this.getAcc(branch, accNumber);
            if(acc!=null){
                if(acc.getBalance()>=value){

                    double postBalance = acc.getBalance()-value;
                    acc.setBalance(postBalance);

                    Date date = new Date();
                    Transaction t = new Transaction("C-WD-"+value,date.toString(),postBalance);

                    ArrayList<Transaction> transactions = acc.getTransactions();
                    transactions.add(t);
                    acc.setTransactions(transactions);

                    response = "Withdrawal succeeded";

                }
                else{
                    response = "Insufficient balance";
                }

            }
        return response;
    }
    
    public String transfer(String branchEnviando, int accNumberEnviando, String branchRecebendo, int accNumberRecebendo, double value){
        
        String response="";
        boolean sendCheck=false;
        boolean benefCheck=false;
        
        Account accEnviando = this.getAcc(branchEnviando, accNumberEnviando);
        if(accEnviando.getBalance()>=value){
            sendCheck = true;
        }
        else{
            response="Insufficient balance";
        }

        
        Account accRecebendo = this.getAcc(branchRecebendo, accNumberRecebendo);
        if(accRecebendo!=null){
            benefCheck=true;
        }
        else{
            response="Account not found";
        }
        
        if(sendCheck && benefCheck){
            
            double postBalance1 = accEnviando.getBalance()-value;
            accEnviando.setBalance(postBalance1);
            
            double postBalance2 = accRecebendo.getBalance()+value;
            accRecebendo.setBalance(postBalance2);
            
            Date date = new Date();
            
            Transaction t1 = new Transaction("C-TF-"+value,date.toString(),postBalance1);
            ArrayList<Transaction> transactions1 = accEnviando.getTransactions();
            transactions1.add(t1);
            accEnviando.setTransactions(transactions1);
            
            Transaction t2 = new Transaction("D-TF-"+value,date.toString(),postBalance2);
            ArrayList<Transaction> transactions2 = accRecebendo.getTransactions();
            transactions2.add(t2);
            accRecebendo.setTransactions(transactions2);
            
            response = "Transfer succeeded";
            
        }
        
        return response;
    }
    
    public double getBalance(String email, String branch, int accNumber){
        
        double balance=0;
        for (Customer me : customers){
            if(me.getEmail().equals(email)){
                
                ArrayList<Account> accounts = me.getAccounts();
                for(Account acc : accounts){

                    if(acc.getBranch().equals(branch) && acc.getAccNumber()==accNumber){
                        
                        balance=acc.getBalance();
                        return balance;
                    }
                }
            }
        }
        return balance;
    }
    
}
