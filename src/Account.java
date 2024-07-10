public class Account {
    private int id;
    private String accountNumber;
    private double balance;
    private String owner;

    public Account(int id, String accountNumber, double balance, String owner) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }
}
