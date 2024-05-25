public class LoanAccount extends Account {
    private double apr;

    public LoanAccount(String accountNumber, double initialBalance, double apr) {
        super(accountNumber, initialBalance);
        this.apr = apr;
    }

    public void applyInterest() {
        double interest = balance * apr / 100;
        balance += interest;
        transactions.add("Interest Applied: $" + interest);
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Withdrawals are not allowed on loan accounts.");
    }
}
