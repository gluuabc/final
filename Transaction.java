public class Transaction {
    private String name;
    private double amount;

    public Transaction(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    //rewrite tostring for output
    @Override
    public String toString() {
        return name + ": $" + amount;
    }

}
