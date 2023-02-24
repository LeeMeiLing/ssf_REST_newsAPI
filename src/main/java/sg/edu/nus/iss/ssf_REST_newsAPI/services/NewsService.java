package sg.edu.nus.iss.ssf_REST_newsAPI.services;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;


@Service
public class NewsService {

    @Value("${news.key}")
	private String apiKey;

    // GET /https://newsapi.org/v2/top-headlines?country=us&apiKey=94b4d80e48ed48cdaa9a7fd9ce350654

    private final String URL = "https://newsapi.org/v2/top-headlines";

    public void getHeadline(String country, String category) {

        // Build the URL
        String url = UriComponentsBuilder.fromUriString(URL)
                .queryParam("apiKey", apiKey)
                .queryParam("country", country)
                .queryParam("category", category)
                .toUriString();

        // Make the request
        RequestEntity<Void> req = RequestEntity.get(url)
                .accept(MediaType.APPLICATION_JSON)
                //.header("X-Api-Key", apiKey)   // alternative way to pass in apiKey as specified in documentation
                .build();

        // Make the call to News API
        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = template.exchange(req, String.class);

        System.out.printf("Status code: %s\n", resp.getStatusCode());

		String payload = resp.getBody();

		// System.out.printf("Payload: %s\n", payload);

        // convert payload to json object
        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject json = reader.readObject();

        Integer totalResult = json.getInt("totalResults");
        System.out.println("10 top headline from the total results of " + totalResult + " news:  ");

        JsonArray arr = json.getJsonArray("articles");
        
        String title;
        // display the first 10 headlines only
        for (int i = 0; i <10; i++){

            json = arr.getJsonObject(i);
            title = json.getString("title");
            System.out.println( "Headline " + (i+1) + " : " + title );

        }

    }

}
