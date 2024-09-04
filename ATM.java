import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM extends JFrame {
    private BankAccount account;
    private JTextField amountField;
    private JTextArea resultArea;
    private JButton depositButton, withdrawButton, checkBalanceButton;

    public ATM(BankAccount account) {
        this.account = account;
        initComponents();
    }

    private void initComponents() {
        setTitle("ATM Interface");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        JLabel amountLabel = new JLabel("Montant:");
        amountField = new JTextField();
        depositButton = new JButton("Dépôt");
        withdrawButton = new JButton("Retrait");
        checkBalanceButton = new JButton("Vérifier Solde");
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        add(amountLabel);
        add(amountField);
        add(depositButton);
        add(withdrawButton);
        add(checkBalanceButton);
        add(resultArea);

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    account.deposit(amount);
                    resultArea.setText("Dépôt réussi.\nSolde actuel: " + account.getBalance());
                } catch (NumberFormatException ex) {
                    resultArea.setText("Veuillez entrer un montant valide.");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    if (account.withdraw(amount)) {
                        resultArea.setText("Retrait réussi.\nSolde actuel: " + account.getBalance());
                    } else {
                        resultArea.setText("Solde insuffisant.");
                    }
                } catch (NumberFormatException ex) {
                    resultArea.setText("Veuillez entrer un montant valide.");
                }
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultArea.setText("Solde actuel: " + account.getBalance());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BankAccount account = new BankAccount(1000); // Solde initial
                ATM atm = new ATM(account);
                atm.setVisible(true);
            }
        });
    }
}
