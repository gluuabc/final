public class LoanAccount extends Account {
    private double apr;

    public LoanAccount(int accountNumber, String accountHolder, double balance, double apr, int pin) {
        super(accountNumber, accountHolder, balance, pin);
        this.apr = apr;
    }

    @Override
    public void applyInterest() {
        double origin=balance;
        balance += balance * apr;
        Transaction e = new Transaction("interest", balance-origin);
        transactions.add(e);
    }
}
