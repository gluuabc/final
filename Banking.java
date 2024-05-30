import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Banking extends JFrame {
    private Map<Integer, Account> accounts = new HashMap<>();
    private JTextArea outputArea;

    //initial page
    public Banking() {
        setTitle("Bank Account Management System");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        //create and add bottom  
        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(10, 10, 150, 25);
        add(createAccountButton);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(200, 10, 150, 25);
        add(loginButton);

        //botton action login/create account
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        //Status text prompt
        outputArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(10, 50, 360, 50);
        add(scrollPane);
        outputArea.setEditable(false);
        outputArea.setText("welcome to the bank. new user create account first");

        setVisible(true);
    }

    //create account page
    private void createAccount() {
      //field and combo for enter user input
        JTextField accountNumberField = new JTextField();
        JTextField accountHolderField = new JTextField();
        JTextField initialBalanceField = new JTextField();
        JPasswordField pinField = new JPasswordField(); // Use JPasswordField for PIN
        JComboBox<String> accountTypeCombo = new JComboBox<>(new String[]{"Savings", "Checking", "Loan"});
        JTextField interestField = new JTextField();
        JTextField overdraftField = new JTextField();
        JTextField aprField = new JTextField();

        //create panel and add lable and bottom
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Account Number:"));
        panel.add(accountNumberField);
        panel.add(new JLabel("Account Holder:"));
        panel.add(accountHolderField);
        panel.add(new JLabel("Initial Balance:"));
        panel.add(initialBalanceField);
        panel.add(new JLabel("PIN (number):"));
        panel.add(pinField);
        panel.add(new JLabel("Account Type:"));
        panel.add(accountTypeCombo);
        panel.add(new JLabel("Interest Rate (Savings):"));
        panel.add(interestField);
        panel.add(new JLabel("Overdraft Limit (Checking):"));
        panel.add(overdraftField);
        panel.add(new JLabel("APR (Loan):"));
        panel.add(aprField);

        //create account action
        int result = JOptionPane.showConfirmDialog(null, panel, "Create Account", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int accountNumber = Integer.parseInt(accountNumberField.getText());
                String accountHolder = accountHolderField.getText();
                double initialBalance = Double.parseDouble(initialBalanceField.getText());
                int pin = Integer.parseInt(new String(pinField.getPassword())); // Get PIN as int
                String accountType = accountTypeCombo.getSelectedItem().toString();
                Account account = null;

                switch (accountType) {
                    case "Savings":
                        double interestRate = Double.parseDouble(interestField.getText());
                        account = new SavingsAccount(accountNumber, accountHolder, initialBalance, interestRate, pin);
                        break;
                    case "Checking":
                        double overdraftLimit = Double.parseDouble(overdraftField.getText());
                        account = new CheckingAccount(accountNumber, accountHolder, initialBalance, overdraftLimit, pin);
                        break;
                    case "Loan":
                        double apr = Double.parseDouble(aprField.getText());
                        account = new LoanAccount(accountNumber, accountHolder, initialBalance, apr, pin);
                        break;
                    default:
                        outputArea.setText("Invalid account type");
                        return;
                }
                //check if account number is already taken
                boolean accountExists = false;
                for (Map.Entry<Integer, Account> mapElement : accounts.entrySet()) {
                    int value = mapElement.getValue().getAccountNumber();
                    if (accountNumber == value) {
                        accountExists = true;
                        outputArea.setText("An account with the same account number already exists. Please use a different account number.");
                        break; 
                    }
                }
                
                if (!accountExists) {
                    accounts.put(accountNumber, account);
                    outputArea.setText("Account created successfully.");
                }                
              
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input. Please enter valid numbers.");
            }
        }
    }

    //login page
    private void login() {
        JTextField accountNumberField = new JTextField();
        JPasswordField pinField = new JPasswordField(); // Use JPasswordField for PIN

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Account Number:"));
        panel.add(accountNumberField);
        panel.add(new JLabel("PIN:"));
        panel.add(pinField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int accountNumber = Integer.parseInt(accountNumberField.getText());
                int pin = Integer.parseInt(new String(pinField.getPassword())); // Get PIN as int
                Account account = accounts.get(accountNumber);

                if (account != null && account.verifyPin(pin)) { // Verify PIN
                    showAccountActions(account);
                    outputArea.setText("successful login!");
                } else {
                    outputArea.setText("Invalid account number or PIN.");
                }
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input. Please enter valid numbers.");
            }
        }
    }

    //account page (after login)
    private void showAccountActions(Account account) {
        JTextField amountField = new JTextField();
        amountField.setSize(100,100);
        JTextArea outcome = new JTextArea(5, 25);
        JScrollPane outcome1 = new JScrollPane(outcome);
        outcome1.setBounds(10, 50, 100, 25);
        outcome.setEditable(false);

        //JPanel for text input
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Amount of money deposit/withdraw/transfer:"));
        panel.add(amountField);
        panel.add(new JLabel("outcome:"));
        panel.add(outcome1);

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transferButton = new JButton("Transfer");
        JButton applyInterestButton = new JButton("Apply Interest");
        JButton viewBalanceButton = new JButton("View Balance");
        JButton changePinButton = new JButton("Change Pin");
        JButton viewTransactionsButton = new JButton("View Transactions");

        //bottom action
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                account.deposit(amount);
                outcome.setText("Deposited: $" + amount);
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                outcome.setText(account.withdraw(amount));
            }
        });

        //transfer in a JOptionPane
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String destinationAccountStr = JOptionPane.showInputDialog("Enter destination account number:");
                try {
                    int destinationAccountNumber = Integer.parseInt(destinationAccountStr);
                    Account toAccount = accounts.get(destinationAccountNumber);
                    if (toAccount != null && toAccount!= account) {
                        double amount = Double.parseDouble(amountField.getText());
                        outcome.setText(account.transfer(toAccount, amount));
                    } else {
                      outcome.setText("Destination account not found.");
                    }
                } catch (NumberFormatException ex) {
                  outcome.setText("Invalid account number or amount.");
                }
            }
        });

        //change pin in a JOptionPane
        changePinButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String newPinStr = JOptionPane.showInputDialog("Enter your new pin:");
              try {
                int newPin = Integer.parseInt(newPinStr); 
                  
                  if (newPin!= account.pin) {
                    account.changePin(newPin);
                      outcome.setText("you change your pin successfully!" );
                  } else {
                    outcome.setText("you did not make change");
                  }
              } catch (NumberFormatException ex) {
                outcome.setText("no change can be made");
              }
          }
      });

        applyInterestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                account.applyInterest();
                outcome.setText("Interest applied (checking account no change). New balance: $" + account.getBalance());
            }
        });

        viewBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              outcome.setText("Current balance: $" + account.getBalance());
            }
        });

        viewTransactionsButton.addActionListener(new ActionListener() { // Add action listener for the new button
          @Override
          public void actionPerformed(ActionEvent e) {
            List<Transaction> transactions = account.getTransactions();
            StringBuilder transactionHistory = new StringBuilder("Transaction History:\n");
            for (Transaction transaction : transactions) {
                transactionHistory.append(transaction.toString()).append("\n");
            }
            outcome.setText(transactionHistory.toString());
          }
      });

        //put all to the frame
        JFrame actionFrame = new JFrame("Account Actions");
        actionFrame.setSize(300, 400);
        actionFrame.setLayout(new BoxLayout(actionFrame.getContentPane(), BoxLayout.Y_AXIS));
        actionFrame.add(new JLabel("Select action:"));
        actionFrame.add(depositButton);
        actionFrame.add(withdrawButton);
        actionFrame.add(transferButton);
        actionFrame.add(applyInterestButton);
        actionFrame.add(viewBalanceButton);
        actionFrame.add(changePinButton);
        actionFrame.add(viewTransactionsButton);
        actionFrame.add(panel);
        actionFrame.setVisible(true);
    }


    public static void main(String[] args) {
        new Banking();
    }
}
