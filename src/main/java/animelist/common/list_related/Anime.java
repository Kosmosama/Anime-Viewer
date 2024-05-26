package animelist.common.list_related;

public class Anime implements Comparable<Anime> {
    private int id;
    private String imageUrl;
    private String name;
    private String genre;
    private int episodes;
    private int year;
    private float score;

    /**
     * Constructs a new Anime object with the specified attributes.
     * @param id The id of the anime.
     * @param imageUrl The image URL of the anime.
     * @param name The name of the anime.
     * @param genre The list of genres associated with the anime.
     * @param episodes The number of episodes of the anime.
     * @param year The release year of the anime.
     * @param score The score/rating of the anime.
     */
    public Anime(int id, String imageUrl, String name, String genre, int episodes, int year, float score) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.genre = genre;
        this.episodes = episodes;
        this.year = year;
        this.score = score;
    }

    /**
     * Compares this Anime with another Anime based on their ids.
     * @param other The Anime to compare to.
     * @return A negative integer, zero, or a positive integer as this Anime's id is less than, equal to, or greater than the specified Anime's id.
     */
    @Override
    public int compareTo(Anime other) {
        return Integer.compare(this.id, other.id);
    }

    /**
     * Returns a string representation of the Anime object.
     * @return A string representation of the Anime object.
     */
    @Override
    public String toString() {
        return "Anime{" +
                "image='" + imageUrl + '\'' +
                ", name='" + name + '\'' +
                ", genres=" + genre +
                ", episodes=" + episodes +
                ", year=" + year +
                ", score=" + score +
                '}';
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void getImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

