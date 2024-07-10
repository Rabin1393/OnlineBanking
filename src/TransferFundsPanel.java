import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TransferFundsPanel extends JPanel {
    private JTextField fromAccountField;
    private JTextField toAccountField;
    private JTextField amountField;
    private JLabel statusLabel;

    public TransferFundsPanel() {
        setLayout(new GridLayout(5, 2));

        add(new JLabel("From Account:"));
        fromAccountField = new JTextField();
        add(fromAccountField);

        add(new JLabel("To Account:"));
        toAccountField = new JTextField();
        add(toAccountField);

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        statusLabel = new JLabel();
        add(statusLabel);

        JButton transferButton = new JButton("Transfer Funds");
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromAccount = fromAccountField.getText();
                String toAccount = toAccountField.getText();
                double amount = Double.parseDouble(amountField.getText());

                try {
                    AccountService service = new AccountService();
                    service.transferFunds(fromAccount, toAccount, amount);
                    statusLabel.setText("Transfer successful");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    statusLabel.setText("Transfer failed");
                }
            }
        });
        add(transferButton);
    }
}
