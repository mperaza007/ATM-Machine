public class User {
    private String username;
    private String password;
    private double accBalance;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.accBalance = 0.0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getAccBalance() {
        return accBalance;
    }

    public void deposit(double amount) {
        accBalance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > accBalance) {
            return false;
        }
        accBalance -= amount;
        return true;
    }
}