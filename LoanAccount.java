public class LoanAccount extends Account {
    private double apr;

    public LoanAccount(String accountNumber, String accountHolder, double initialBalance, double apr) {
        super(accountNumber, accountHolder, initialBalance);
        this.apr = apr;
    }

    @Override
    public void applyInterest() {
        double interest = balance * (apr / 100);
        balance += interest;
    }
}
