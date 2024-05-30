import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected String accountHolder;
    protected double balance;
    protected int accountNumber;
    protected int pin;
    protected List<Transaction> transactions; // Add a list to store transaction history


    public Account(int accountNumber, String accountHolder, double balance, int pin) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.pin = pin;
        this.transactions = new ArrayList<>(); 
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        Transaction e = new Transaction("deposit", amount);
            transactions.add(e);
        balance += amount;
    }

    public String withdraw(double amount) {
        String outp = "Withdrawn: $" + amount;
        if (balance >= amount) {
            balance -= amount;
            Transaction e = new Transaction("withdraw", amount);
            transactions.add(e);
        } else {
            outp="Insufficient funds";
        }
        return outp;
    }

    public String transfer(Account toAccount, double amount) {
        String outp = "Transferred: $" + amount + " to account: " + toAccount.getAccountNumber();
        if (balance >= amount) {
            balance -= amount;
            toAccount.deposit(amount);
            Transaction e = new Transaction("transfer to account number" + toAccount.getAccountNumber(), amount);
            transactions.add(e);
        } else {
            outp="Insufficient funds";
        }
        return outp;
    }

    public abstract void applyInterest();

    public boolean verifyPin(int pin) {
        return this.pin == pin;
    }

    public void changePin(int newPin){
        pin = newPin;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
