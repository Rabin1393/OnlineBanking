import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ViewBalancePanel extends JPanel {
    private JTextField accountNumberField;
    private JLabel balanceLabel;

    public ViewBalancePanel() {
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Account Number:"));
        accountNumberField = new JTextField();
        add(accountNumberField);

        balanceLabel = new JLabel();
        add(balanceLabel);

        JButton viewButton = new JButton("View Balance");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                try {
                    AccountService service = new AccountService();
                    Account account = service.getAccountByNumber(accountNumber);
                    if (account != null) {
                        balanceLabel.setText("Balance: " + account.getBalance());
                    } else {
                        balanceLabel.setText("Account not found");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(viewButton);
    }
}
