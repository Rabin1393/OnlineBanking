import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SignUpPanel extends JPanel {
    private JTextField accountNumberField;
    private JTextField ownerField;
    private JTextField balanceField;
    private JLabel statusLabel;

    public SignUpPanel() {
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Account Number:"));
        accountNumberField = new JTextField();
        add(accountNumberField);

        add(new JLabel("Owner:"));
        ownerField = new JTextField();
        add(ownerField);

        add(new JLabel("Initial Balance:"));
        balanceField = new JTextField();
        add(balanceField);

        statusLabel = new JLabel();
        add(statusLabel);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                String owner = ownerField.getText();
                double balance = Double.parseDouble(balanceField.getText());

                try {
                    AccountService service = new AccountService();
                    service.createAccount(accountNumber, owner, balance);
                    statusLabel.setText("Account created successfully");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    statusLabel.setText("Account creation failed");
                }
            }
        });
        add(signUpButton);
    }
}
