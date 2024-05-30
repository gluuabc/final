public class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(int accountNumber, String accountHolder, double balance, double overdraftLimit, int pin) {
        super(accountNumber, accountHolder, balance, pin);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public String withdraw(double amount) {
        if (amount>0){
        String outp = "Withdrawn: $" + amount;
        if (amount >= balance+overdraftLimit) {
            balance -= amount;
            Transaction e = new Transaction("withdraw", amount);
            transactions.add(e);
        } else {
            outp="Insufficient funds including overdraft limit";
        }
        return outp;
    }
    return "not valid number";
    }

    @Override
    public String transfer(Account toAccount, double amount){
        String outp = "Transferred: $" + amount + " to account: " + toAccount.getAccountNumber();
        if (amount >= balance+overdraftLimit) {
            balance -= amount;
            toAccount.deposit(amount);
            Transaction e = new Transaction("transfer to account number" + toAccount.getAccountNumber(), amount);
            transactions.add(e);
        } else {
            outp="Insufficient funds";
        }
        return outp;
    }

    @Override
    public void applyInterest() {
        // Checking accounts typically don't have interest
    }
}
