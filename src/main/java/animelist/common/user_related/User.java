package animelist.common.user_related;

import java.io.*;

/**
 * Represents a user in the system.
 */
public class User extends UserManagement implements Comparable<User> {
    private String username;
    private String password;

    /**
     * Constructs a new User with the specified username and password.
     *
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
    @Override
    public void saveUser() {
        String filename = "src/main/resources/animelist/data/users.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(this.username.toLowerCase() + "," + this.password.toLowerCase() + ",user");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the user exists in the file and if the password matches.
     *
     * @return -1 if the user doesn't exist, 0 if only the username is correct, 1 if both the username and password are correct.
     */
    public int exists() {
        String filename = "src/main/resources/animelist/data/users.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                if (userInfo.length >= 3 && userInfo[0].equalsIgnoreCase(username.toLowerCase())) {
                    // Username found, check password
                    if (userInfo[1].equals(password.toLowerCase())) {
                        // Password matches
                        return 1;
                    } else {
                        // Password does not match
                        return 0;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // User doesn't exist
        return -1;
    }

    /**
     * Compares this user with another user based on their usernames.
     *
     * @param other The user to compare to.
     * @return A negative integer, zero, or a positive integer as this user is less than, equal to, or greater than the specified user.
     */
    @Override
    public int compareTo(User other) {
        return this.username.compareTo(other.username);
    }

    /**
     * Returns a string representation of the user.
     *
     * @return A string representation of the user.
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    // Getters and setters
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