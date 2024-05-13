package animelist.common;

/**
 * Represents an admin user in the system.
 */
public class Admin extends User {

    /**
     * Constructs a new Admin with the specified username and password.
     * @param username The username of the admin.
     * @param password The password of the admin.
     */
    public Admin(String username, String password) {
        super(username, password);
    }

    /**
     * Saves the username of the admin who banned it and adds the banned anime
     * to the banned animes files, so it cannot be seen by the user.
     * @param animeID The ID of the anime to be banned.
     */
    public void banAnime(int animeID) {
    }

    /**
     * Saves the admin to the user file.
     * @param admin The admin to be saved.
     */
    public void saveAdmin(Admin admin) {
    }

    /**
     * Returns a string representation of the admin.
     * @return A string representation of the admin.
     */
    @Override
    public String toString() {
        return "Admin{" +
                "username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}
