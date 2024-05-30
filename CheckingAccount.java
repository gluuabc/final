public class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(int accountNumber, String accountHolder, double balance, double overdraftLimit, int pin) {
        super(accountNumber, accountHolder, balance, pin);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public String withdraw(double amount) {
        String outpw = "may include overdraft limit";
        return super.withdraw(amount)+ outpw;
    }

    @Override
    public void applyInterest() {
        // Checking accounts typically don't have interest
    }
}
