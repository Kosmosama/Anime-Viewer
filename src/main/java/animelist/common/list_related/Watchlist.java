package animelist.common.list_related;

import animelist.common.other.AnimeService;
import animelist.interfaces.IFilterable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a watchlist containing anime.
 */
public class Watchlist implements IFilterable {
    private List<Anime> watchlist;
    private AnimeService animeService;
    private String username;
    private String filePath;

    /**
     * Constructor to create a watchlist for a specific user.
     *
     * @param username the username of the user
     */
    public Watchlist(String username) {
        this.username = username;
        this.animeService = new AnimeService();
        this.watchlist = new ArrayList<>();

        filePath = "src/main/resources/animelist/data/watchlists/" + username + "_watchlist.txt";

        loadWatchlist();
    }

    /**
     * Loads the watchlist from a file.
     */
    public void loadWatchlist() {
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    int animeId = Integer.parseInt(line);
                    Anime anime = animeService.fetchAnimeById(animeId);
                    if (anime != null) {
                        watchlist.add(anime);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds an anime to the watchlist and saves it to the file.
     *
     * @param anime the Anime object to add
     */
    public void addAnime(Anime anime) {
        watchlist.add(anime);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(String.valueOf(anime.getId()));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes an anime from the watchlist and updates the file.
     *
     * @param anime the Anime object to remove
     */
    public void removeAnime(Anime anime) {
        watchlist.remove(anime);
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Anime a : watchlist) {
                    writer.write(String.valueOf(a.getId()));
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the watchlist as an observable list.
     *
     * @return the observable watchlist
     */
    public ObservableList<Anime> getWatchlist() {
        return FXCollections.observableArrayList(watchlist);
    }


    /**
     * Filters the list of anime in the Watchlist.
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