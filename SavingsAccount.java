public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(int accountNumber, String accountHolder, double balance, double interestRate, int pin) {
        super(accountNumber, accountHolder, balance, pin);
        this.interestRate = interestRate;
    }

    @Override
    public void applyInterest() {
        balance += balance * interestRate;
    }
}
