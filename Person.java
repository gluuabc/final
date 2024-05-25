package main.java;
import java.util.ArrayList;
import java.util.List;

public class Person{
    private int num;
    private int pin;
    protected double balance;
    protected List<String> transactions;
    private String name;

    public Person(String name, int newPin, double balance){
        this.name= name;
        pin=newPin;
        this.balance=balance;
        this.transactions = new ArrayList<>();
    }

    public int getaccountNumber(){return num;}

    public int getPin(){return pin;}

    public double checkBalance(){
        return balance;
    }

  

    public String getName(){
        return name;
    }

    public void withdraw(double amount){
        if (amount > 0 && balance >= amount) {balance-= amount;}
        else{
        System.out.println("you broke");
        }
    }
    
    public void deposit(double amount){
        if (amount > 0){balance+= amount;}
        else {
            System.out.println("Invalid deposit amount.");
        }
    }

        public void transfer(Account toAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            withdraw(amount);
            toAccount.deposit(amount);
            transactions.add("Transferred: $" + amount + " to Account " + toAccount.getAccountNumber());
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

        public void viewTransactions() {
        System.out.println("Transactions for Account " + accountNumber + ":");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }

public void changePin(int newPin){
        pin = newPin;
    }


}