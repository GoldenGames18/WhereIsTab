package src;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Fetch implements AutoCloseable {

    private String apiKey;

    private URL url;
    private HttpsURLConnection connection;

    public Fetch(URL url, String apiKey)  {
        this.apiKey = apiKey;
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    public void createConnection() throws IOException {
        this.connection = (HttpsURLConnection) this.url.openConnection();
        this.connection.setRequestMethod("GET");
        this.connection.setRequestProperty("X-API-KEY", this.apiKey);
    }

    public int statusCode() throws IOException {
        return this.connection.getResponseCode();
    }

    public String extractData() throws IOException {
        String response = "";
        try(BufferedReader in = new BufferedReader(new InputStreamReader(this.connection.getInputStream()))){
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }
        }
        return response;
    }

    @Override
    public void close() throws Exception {
        this.connection.disconnect();
    }
}
