public class LoanAccount extends Person {
    private double apr;

    public LoanAccount(String name, int newPin, double balance, double apr) {
        super(name, newPin, balance);
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
