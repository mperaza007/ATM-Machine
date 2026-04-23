import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, User> users = new HashMap<>();

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public User register(String username, String password) {
        User user = new User(username, password);
        users.put(username, user);
        return user;
    }
}
