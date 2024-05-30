public abstract class Account {
    protected int accountNumber;
    protected String accountHolder;
    protected double balance;
    protected int pin;

    public Account(int accountNumber, String accountHolder, double balance, int pin) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.pin = pin;
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
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void transfer(Account toAccount, double amount) {
        if (balance >= amount) {
            balance -= amount;
            toAccount.deposit(amount);
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public abstract void applyInterest();

    public boolean verifyPin(int pin) {
        return this.pin == pin;
    }
}
