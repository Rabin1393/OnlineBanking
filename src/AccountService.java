import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountService {
    public Account getAccountByNumber(String accountNumber) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM accounts WHERE accountNumber = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, accountNumber);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Account(
                    resultSet.getInt("id"),
                    resultSet.getString("accountNumber"),
                    resultSet.getDouble("balance"),
                    resultSet.getString("owner")
            );
        }

        return null;
    }

    public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        connection.setAutoCommit(false);

        try {
            Account fromAccount = getAccountByNumber(fromAccountNumber);
            Account toAccount = getAccountByNumber(toAccountNumber);

            if (fromAccount != null && toAccount != null && fromAccount.getBalance() >= amount) {
                updateBalance(connection, fromAccountNumber, fromAccount.getBalance() - amount);
                updateBalance(connection, toAccountNumber, toAccount.getBalance() + amount);
                connection.commit();
            } else {
                connection.rollback();
                throw new SQLException("Transfer failed");
            }
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
    }

    private void updateBalance(Connection connection, String accountNumber, double newBalance) throws SQLException {
        String updateQuery = "UPDATE accounts SET balance = ? WHERE accountNumber = ?";
        PreparedStatement statement = connection.prepareStatement(updateQuery);
        statement.setDouble(1, newBalance);
        statement.setString(2, accountNumber);
        statement.executeUpdate();
    }
    public void createAccount(String accountNumber, String owner, double balance) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String insertQuery = "INSERT INTO accounts (accountNumber, owner, balance) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertQuery);
        statement.setString(1, accountNumber);
        statement.setString(2, owner);
        statement.setDouble(3, balance);
        statement.executeUpdate();
        connection.close();
    }
}
