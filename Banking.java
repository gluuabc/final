import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Banking extends JFrame {
    private Map<String, Account> accounts = new HashMap<>();
    private JTextField accountNumberField, accountHolderField, balanceField, amountField, interestField, overdraftField, aprField;
    private JTextArea outputArea;

    public Banking() {
        setTitle("Bank Account Management System");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberLabel.setBounds(10, 10, 150, 25);
        add(accountNumberLabel);

        accountNumberField = new JTextField();
        accountNumberField.setBounds(160, 10, 150, 25);
        add(accountNumberField);

        JLabel accountHolderLabel = new JLabel("Account Holder:");
        accountHolderLabel.setBounds(10, 40, 150, 25);
        add(accountHolderLabel);

        accountHolderField = new JTextField();
        accountHolderField.setBounds(160, 40, 150, 25);
        add(accountHolderField);

        JLabel balanceLabel = new JLabel("Initial Balance:");
        balanceLabel.setBounds(10, 70, 150, 25);
        add(balanceLabel);

        balanceField = new JTextField();
        balanceField.setBounds(160, 70, 150, 25);
        add(balanceField);

        JLabel accountTypeLabel = new JLabel("Account Type:");
        accountTypeLabel.setBounds(10, 100, 150, 25);
        add(accountTypeLabel);

        JComboBox<String> accountTypeCombo = new JComboBox<>(new String[]{"Savings", "Checking", "Loan"});
        accountTypeCombo.setBounds(160, 100, 150, 25);
        add(accountTypeCombo);

        JLabel interestLabel = new JLabel("Interest Rate:");
        interestLabel.setBounds(10, 130, 150, 25);
        add(interestLabel);

        interestField = new JTextField();
        interestField.setBounds(160, 130, 150, 25);
        add(interestField);

        JLabel overdraftLabel = new JLabel("Overdraft Limit:");
        overdraftLabel.setBounds(10, 160, 150, 25);
        add(overdraftLabel);

        overdraftField = new JTextField();
        overdraftField.setBounds(160, 160, 150, 25);
        add(overdraftField);

        JLabel aprLabel = new JLabel("APR:");
        aprLabel.setBounds(10, 190, 150, 25);
        add(aprLabel);

        aprField = new JTextField();
        aprField.setBounds(160, 190, 150, 25);
        add(aprField);

        JButton createButton = new JButton("Create Account");
        createButton.setBounds(10, 220, 150, 25);
        add(createButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(10, 250, 150, 25);
        add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(10, 280, 150, 25);
        add(withdrawButton);

        JButton transferButton = new JButton("Transfer");
        transferButton.setBounds(10, 310, 150, 25);
        add(transferButton);

        JButton applyInterestButton = new JButton("Apply Interest");
        applyInterestButton.setBounds(10, 340, 150, 25);
        add(applyInterestButton);

        JButton viewBalanceButton = new JButton("View Balance");
        viewBalanceButton.setBounds(10, 370, 150, 25);
        add(viewBalanceButton);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 400, 150, 25);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(160, 400, 150, 25);
        add(amountField);

        outputArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(10, 430, 360, 120);
        add(scrollPane);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount(accountTypeCombo.getSelectedItem().toString());
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTransaction("deposit");
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTransaction("withdraw");
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTransaction("transfer");
            }
        });

        applyInterestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyInterest();
            }
        });

        viewBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewBalance();
            }
        });

        setVisible(true);
    }

    private void createAccount(String accountType) {
        String accountNumber = accountNumberField.getText();
        String accountHolder = accountHolderField.getText();
        double initialBalance = Double.parseDouble(balanceField.getText());
        Account account = null;

        switch (accountType) {
            case "Savings":
                double interestRate = Double.parseDouble(interestField.getText());
                account = new SavingsAccount(accountNumber, accountHolder, initialBalance, interestRate);
                break;
            case "Checking":
                double overdraftLimit = Double.parseDouble(overdraftField.getText());
                account = new CheckingAccount(accountNumber, accountHolder, initialBalance, overdraftLimit);
                break;
            case "Loan":
                double apr = Double.parseDouble(aprField.getText());
                account = new LoanAccount(accountNumber, accountHolder, initialBalance, apr);
                break;
            default:
                outputArea.setText("Invalid account type");
                return;
        }

        accounts.put(accountNumber, account);
        outputArea.setText("Account created successfully.");
    }

    private void performTransaction(String type) {
        String accountNumber = accountNumberField.getText();
        double amount = Double.parseDouble(amountField.getText());
        Account account = accounts.get(accountNumber);

        if (account != null) {
            switch (type) {
                case "deposit":
                    account.deposit(amount);
                    outputArea.setText("Deposited: $" + amount);
                    break;
                case "withdraw":
                    account.withdraw(amount);
                    outputArea.setText("Withdrawn: $" + amount);
                    break;
                case "transfer":
                    String destinationAccountNumber = JOptionPane.showInputDialog("Enter destination account number:");
                    Account destinationAccount = accounts.get(destinationAccountNumber);
                    if (destinationAccount != null) {
                        account.transfer(destinationAccount, amount);
                        outputArea.setText("Transferred: $" + amount + " to " + destinationAccountNumber);
                    } else {
                        outputArea.setText("Destination account not found.");
                    }
                    break;
            }
        } else {
            outputArea.setText("Account not found.");
        }
    }

    private void applyInterest() {
        String accountNumber = accountNumberField.getText();
        Account account = accounts.get(accountNumber);

        if (account != null) {
            account.applyInterest();
            outputArea.setText("Interest applied.");
        } else {
            outputArea.setText("Account not found.");
        }
    }

    private void viewBalance() {
        String accountNumber = accountNumberField.getText();
        Account account = accounts.get(accountNumber);

        if (account != null) {
            outputArea.setText("Balance: $" + account.getBalance());
        } else {
            outputArea.setText("Account not found.");
        }
    }

    public static void main(String[] args) {
        new Banking();
    }
}
