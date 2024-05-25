import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Banking implements ActionListener {
  private JFrame mainFrame;
  private JTextField inputField;
  private JLabel balanceLabel;
  
public static Account currreAccount;

  public Banking() {

    mainFrame = new JFrame("Simple Bank Application");
    mainFrame.setVisible(true);
    mainFrame.setSize(400, 400);
    mainFrame.setFont(new Font("Arial",Font.BOLD,18));
    mainFrame.setLayout(new FlowLayout());

    inputField = new JTextField(10);
    balanceLabel = new JLabel("Current balance: " + currreAccount.getBalance());
    JButton depositButton = new JButton("Deposit");
    JButton withdrawButton = new JButton("Withdraw");

    mainFrame.add(inputField);
    mainFrame.add(depositButton);
    mainFrame.add(withdrawButton);
    mainFrame.add(balanceLabel);

    depositButton.addActionListener(this);
    withdrawButton.addActionListener(this);

    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int amount = Integer.parseInt(inputField.getText());
    if (e.getActionCommand().equals("Deposit")) {
      currreAccount.deposit(amount);
    } else {
      currreAccount.withdraw(amount);
    }
    balanceLabel.setText("Current balance: " + currreAccount.getBalance());
    inputField.setText("");
  }


  public static void main(String[] args) {
    new Banking();
  }
}

//yes