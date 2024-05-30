public class LoanAccount extends Account {
    private double apr;

    public LoanAccount(int accountNumber, String accountHolder, double balance, double apr, int pin) {
        super(accountNumber, accountHolder, balance, pin);
        this.apr = apr;
    }

    @Override
    public void applyInterest() {
        balance += balance * apr;
        Transaction e = new Transaction("interest", balance);
        transactions.add(e);
    }
}
