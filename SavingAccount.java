package main.java;

public class SavingAccount extends Person {
    private double interestRate;
    public SavingAccount(String name, int newPin, double interestRate, double balance) {
        super(name, newPin, balance);
        this.interestRate = interestRate;
    }


    public void applyInterest(){
        double interest = balance * interestRate / 100;
        balance += interest;
        transactions.add("Interest Applied: $" + interest);
    }
    
}

//