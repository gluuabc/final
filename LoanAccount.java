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

    // Repay loan
    public void repay(double amount) {
        if (amount > 0) {
            balance -= amount;
            transactions.add(new Transaction("Repayment", amount));
        }
    }

    @Override
    public void deposit(double amount) {
        repay(amount);
    }

    @Override
    public String withdraw(double amount){
        return "you cannot withdraw in loan account";
    }

    @Override
    public String transfer(Account toAccount, double amount) {
        return "you cannot transfer in loan account";
    }

}
