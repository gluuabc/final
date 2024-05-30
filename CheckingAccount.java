public class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(int accountNumber, String accountHolder, double balance, double overdraftLimit, int pin) {
        super(accountNumber, accountHolder, balance, pin);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds, including overdraft limit");
        }
    }

    @Override
    public void applyInterest() {
        // Checking accounts typically don't have interest
    }
}
