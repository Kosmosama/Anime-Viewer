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

public class AnimeService {
    private static final String API_URL = "https://api.jikan.moe/v4/seasons/now";
    private static final int PAGE_LIMIT = 3; // Number of pages to fetch
    private static final int ITEMS_PER_PAGE = 25; // Number of items per page (default for Jikan)

    public ObservableList<Anime> fetchAnimes() {
        ObservableList<Anime> animeList = FXCollections.observableArrayList();

        try {
            for (int page = 1; page <= PAGE_LIMIT; page++) {
                URL url = new URL(API_URL + "?page=" + page);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();
                conn.disconnect();

                JSONObject json = new JSONObject(content.toString());
                JSONArray dataArray = json.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject animeJson = dataArray.getJSONObject(i);

                    JSONObject images = animeJson.getJSONObject("images");
                    String imageUrl = images.getJSONObject("jpg").getString("image_url");

                    Anime anime = new Anime(
                            animeJson.optInt("mal_id", 0),
                            imageUrl,
                            animeJson.getString("title"),
                            animeJson.has("genres") && !animeJson.getJSONArray("genres").isEmpty() ? animeJson.getJSONArray("genres").getJSONObject(0).getString("name") : "N/A",
                            animeJson.optInt("episodes", 0),
                            animeJson.getJSONObject("aired").getJSONObject("prop").getJSONObject("from").optInt("year", 0),
                            (float) animeJson.optDouble("score", 0.0)
                    );

                    animeList.add(anime);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return animeList;
    }
}
