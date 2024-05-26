package animelist.common.list_related;

import animelist.interfaces.IFilterable;
import animelist.common.other.AnimeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents an animelist containing anime.
 */
public class Animelist implements IFilterable {
    private ObservableList<Anime> animelist;
    private AnimeService animeService;

    /**
     * Constructs a new empty Animelist.
     */
    public Animelist() {
        this.animeService = new AnimeService();
        this.animelist = FXCollections.observableArrayList();
        fetchAnimesFromService();
    }

    /**
     * Fetches anime data using AnimeService.
     */
    private void fetchAnimesFromService() {
        this.animelist.addAll(animeService.fetchAnimes());
    }

    /**
     * Gets the ObservableList of animes.
     * @return ObservableList of animes.
     */
    public ObservableList<Anime> getAnimelist() {
        return animelist;
    }

    // Métodos adicionales de la clase Animelist

    public void addAnime(Anime anime) {
        animelist.add(anime);
    }

    public void removeAnime(Anime anime) {
        animelist.remove(anime);
    }

    public void filterList() {
        // Implementar filtrado según criterios
    }

    @Override
    public String toString() {
        return "Animelist{" +
                "animelist=" + animelist +
                '}';
    }
}
