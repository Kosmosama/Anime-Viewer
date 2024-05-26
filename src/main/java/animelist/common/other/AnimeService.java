package animelist.common.other;

import animelist.common.list_related.Anime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Service class for fetching anime data from the Jikan API.
 */
public class AnimeService {

    private static final String API_URL_SEASONAL = "https://api.jikan.moe/v4/seasons/now";
    private static final String API_URL_EXPLICIT = "https://api.jikan.moe/v4/anime/";
    private static final int PAGE_LIMIT = 3; // Number of pages to fetch
    private static final int ITEMS_PER_PAGE = 25; // Number of items per page (default for Jikan)

    /**
     * Fetches the list of current season animes from the Jikan API.
     *
     * @return an ObservableList of Anime objects.
     */
    public ObservableList<Anime> fetchAnimes() {
        ObservableList<Anime> animeList = FXCollections.observableArrayList();

        try {
            for (int page = 1; page <= PAGE_LIMIT; page++) {
                String jsonResponse = getHttpResponse(API_URL_SEASONAL + "?page=" + page);
                JSONArray dataArray = new JSONObject(jsonResponse).getJSONArray("data");
                animeList.addAll(parseAnimeList(dataArray));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return animeList;
    }

    /**
     * Fetches an anime by its ID using the Jikan API.
     *
     * @param animeId the ID of the anime.
     * @return the Anime object or null if an error occurs.
     */
    public Anime fetchAnimeById(int animeId) {
        try {
            String jsonResponse = getHttpResponse(API_URL_EXPLICIT + animeId);
            JSONObject data = new JSONObject(jsonResponse).getJSONObject("data");
            return parseAnime(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Makes an HTTP GET request to the given URL and returns the response as a String.
     *
     * @param urlString the URL to make the request to.
     * @return the response as a String.
     * @throws Exception if an error occurs during the request.
     */
    private String getHttpResponse(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder content = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();

        return content.toString();
    }

    /**
     * Parses a JSONArray of anime data into a list of Anime objects.
     *
     * @param dataArray the JSONArray of anime data.
     * @return a list of Anime objects.
     */
    private ObservableList<Anime> parseAnimeList(JSONArray dataArray) {
        ObservableList<Anime> animeList = FXCollections.observableArrayList();

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject animeJson = dataArray.getJSONObject(i);
            Anime anime = parseAnime(animeJson);
            if (anime != null) {
                animeList.add(anime);
            }
        }

        return animeList;
    }

    /**
     * Parses a JSONObject of anime data into an Anime object.
     *
     * @param animeJson the JSONObject of anime data.
     * @return an Anime object.
     */
    private Anime parseAnime(JSONObject animeJson) {
        JSONObject images = animeJson.getJSONObject("images");
        String imageUrl = images.getJSONObject("jpg").getString("image_url");

        return new Anime(
                animeJson.optInt("mal_id", 0),
                imageUrl,
                animeJson.getString("title"),
                animeJson.has("genres") && !animeJson.getJSONArray("genres").isEmpty() ? animeJson.getJSONArray("genres").getJSONObject(0).getString("name") : "N/A",
                animeJson.optInt("episodes", 0),
                animeJson.getJSONObject("aired").getJSONObject("prop").getJSONObject("from").optInt("year", 0),
                (float) animeJson.optDouble("score", 0.0)
        );
    }
}
