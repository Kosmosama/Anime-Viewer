package animelist.common.list_related;

import animelist.common.list_related.Anime;
import animelist.interfaces.IFilterable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an animelist containing anime.
 */
public class Animelist implements IFilterable {
    private List<Anime> animelist;

    /**
     * Constructs a new empty Animelist.
     */
    public Animelist() {
        this.animelist = new ArrayList<>();
    }

    /**
     * Checks if the given anime is banned in the Animelist.
     * @param anime The anime to check.
     * @return true if the anime is banned, false otherwise.
     */
    public boolean isBanned(Anime anime) {
        return false;
    }

    /**
     * Adds an anime to the Animelist.
     * @param anime The anime to add.
     */
    public void addAnime(Anime anime) {
        // Add to the List
        // Add to the file
    }

    /**
     * Removes an anime from the Animelist.
     * @param anime The anime to remove.
     */
    public void removeAnime(Anime anime) {
        // Remove from the List
        // Remove from the file
    }

    /**
     * Filters the list of anime in the Animelist.
     * This method should be implemented to perform filtering operations on the list of anime.
     */
    public void filterList() {
    }

    /**
     * Returns a string representation of the Animelist.
     * @return A string representation of the Animelist.
     */
    @Override
    public String toString() {
        return "Animelist{" +
                "animelist=" + animelist +
                '}';
    }
}
