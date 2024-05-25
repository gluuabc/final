import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankGUI extends JFrame {
    private JTextField accountNumberField;
    private JTextField amountField;
    private JTextArea transactionArea;
    private Account currentAccount;

    public BankGUI() {
        setTitle("Bank Account Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        accountNumberField = new JTextField();
        amountField = new JTextField();
        transactionArea = new JTextArea();
        transactionArea.setEditable(false);

        JButton createSavingsButton = new JButton("Create Savings Account");
        JButton createCheckingButton = new JButton("Create Checking Account");
        JButton createLoanButton = new JButton("Create Loan Account");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transferButton = new JButton("Transfer");
        JButton viewTransactionsButton = new JButton("View Transactions");

        createSavingsButton.addActionListener(new CreateSavingsListener());
        createCheckingButton.addActionListener(new CreateCheckingListener());
        createLoanButton.addActionListener(new CreateLoanListener());
        depositButton.addActionListener(new DepositListener());
        withdrawButton.addActionListener(new WithdrawListener());
        transferButton.addActionListener(new TransferListener());
        viewTransactionsButton.addActionListener(new ViewTransactionsListener());

        add(new JLabel("Account Number:"));
        add(accountNumberField);
        add(new JLabel("Amount:"));
        add(amountField);
        add(createSavingsButton);
        add(createCheckingButton);
        add(createLoanButton);
        add(depositButton);
        add(withdrawButton);
        add(transferButton);
        add(viewTransactionsButton);
        add(new JScrollPane(transactionArea));

        setVisible(true);
    }

    private class CreateSavingsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String accountNumber = accountNumberField.getText();
            currentAccount = new SavingsAccount(accountNumber, 0, 1.5);
            transactionArea.append("Created Savings Account: " + accountNumber + "\n");
        }
    }

    private class CreateCheckingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String accountNumber = accountNumberField.getText();
            currentAccount = new CheckingAccount(accountNumber, 0);
            transactionArea.append("Created Checking Account: " + accountNumber + "\n");
        }
    }

    private class CreateLoanListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String accountNumber = accountNumberField.getText();
            currentAccount = new LoanAccount(accountNumber, 0, 5);
            transactionArea.append("Created Loan Account: " + accountNumber + "\n");
        }
    }

    private class DepositListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentAccount != null) {
                double amount = Double.parseDouble(amountField.getText());
                currentAccount.deposit(amount);
                transactionArea.append("Deposited: $" + amount + "\n");
            } else {
                transactionArea.append("No account selected.\n");
            }
        }
    }

    private class WithdrawListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentAccount != null) {
                double amount = Double.parseDouble(amountField.getText());
                currentAccount.withdraw(amount);
                transactionArea.append("Withdrew: $" + amount + "\n");
            } else {
                transactionArea.append("No account selected.\n");
            }
        }
    }

    private class TransferListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // For simplicity, this example assumes transferring to a new checking account.
            if (currentAccount != null) {
                String toAccountNumber = JOptionPane.showInputDialog("Enter destination account number:");
                double amount = Double.parseDouble(amountField.getText());
                Account toAccount = new CheckingAccount(toAccountNumber, 0);
                currentAccount.transfer(toAccount, amount);
                transactionArea.append("Transferred: $" + amount + " to Account " + toAccountNumber + "\n");
            } else {
                transactionArea.append("No account selected.\n");
            }
        }
    }

    private class ViewTransactionsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentAccount != null) {
                currentAccount.viewTransactions();
                transactionArea.append("Transactions viewed for Account " + currentAccount.getAccountNumber() + "\n");
            } else {
                transactionArea.append("No account selected.\n");
            }
        }
    }

    public static void main(String[] args) {
        new BankGUI();
    }
}
