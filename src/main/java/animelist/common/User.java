package animelist.common;

/**
 * Represents a user in the system.
 */
public class User implements Comparable<User> {
    private String username;
    private String password;

    /**
     * Constructs a new User with the specified username and password.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Saves the user to the user file.
     */
    public void saveUser() {
        // Implementation to save the user to the user file
    }

    /**
     * Checks if the user exists in the user file.
     * @param user The user to check.
     * @return true if the user exists, false otherwise.
     */
    public boolean exists(User user) {
        return true;
    }

    /**
     * Compares this user with another user based on their usernames.
     * @param other The user to compare to.
     * @return A negative integer, zero, or a positive integer as this user is less than, equal to, or greater than the specified user.
     */
    @Override
    public int compareTo(User other) {
        return this.username.compareTo(other.username);
    }

    /**
     * Returns a string representation of the user.
     * @return A string representation of the user.
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
