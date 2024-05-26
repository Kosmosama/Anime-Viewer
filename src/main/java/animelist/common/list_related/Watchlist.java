package animelist.common.list_related;

import animelist.interfaces.IFilterable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a watchlist containing anime.
 */
public class Watchlist implements IFilterable {
    private List<Anime> watchlist;

    /**
     * Constructs a new empty watchlist.
     */
    public Watchlist() {
        this.watchlist = new ArrayList<>();
    }

    /**
     * Adds an anime to the watchlist.
     * @param anime The anime to add.
     */
    public void addAnime(Anime anime) {
        // Add to the List
        // Add to the file
    }

    /**
     * Updates the information of an anime in the watchlist.
     * @param anime The anime to update.
     */
    public void updateAnime(Anime anime) {
    }

    /**
     * Removes an anime from the watchlist.
     * @param anime The anime to remove.
     */
    public void removeAnime(Anime anime) {
        // Remove from the List
        // Remove from the file
    }

    /**
     * Edits the rating of an anime in the watchlist.
     * @param anime The anime to edit the rating for.
     */
    public void editAnimeRating(Anime anime) {
    }

    /**
     * Filters the list of anime in the Animelist.
     * This method should be implemented to perform filtering operations on the list of anime.
     */
    public void filterList() {
    }

    /**
     * Returns a string representation of the watchlist.
     * @return A string representation of the watchlist.
     */
    @Override
    public String toString() {
        return "Watchlist{" +
                "watchlist=" + watchlist +
                '}';
    }
}