//simple account with interest
public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(int accountNumber, String accountHolder, double balance, double interestRate, int pin) {
        super(accountNumber, accountHolder, balance, pin);
        this.interestRate = interestRate;
    }

    @Override
    public void applyInterest() {
        double origin=balance;
        balance += balance * interestRate;
        Transaction e = new Transaction("interest", balance-origin);
        transactions.add(e);
    }
}
