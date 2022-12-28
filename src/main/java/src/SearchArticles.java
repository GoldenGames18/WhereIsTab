package src;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;

import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

public class SearchArticles implements Runnable{

    private ConcurrentHashMap<String, String> newsletterTwab;
    private String apiKey;
    private JDA jda;
    private boolean isRunning;
    private URL url;

    private HttpsURLConnection connection;

    public SearchArticles(ConcurrentHashMap<String, String> newsletterTwab, String apiKey, JDA jda) {
        this.newsletterTwab = newsletterTwab;
        this.apiKey = apiKey;
        this.jda = jda;
        try{
            this.url = new URL("https://www.bungie.net/platform/Content/Rss/NewsArticles/0");
            this.isRunning = true;
        }catch (MalformedURLException  e){
            this.isRunning = false;
        }



    }

    public void stop(){
        this.isRunning = false;
        System.out.println("STOP THREAD");
    }


    private int createConnection() throws IOException {
        this.connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-API-KEY", this.apiKey);
        return connection.getResponseCode();
    }

    private JsonElement takeData() throws IOException {
        String response = "";
        try(BufferedReader in = new BufferedReader(new InputStreamReader(this.connection.getInputStream()))){
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }
        }
        return jsonConvert(response) ;
    }

    private JsonElement jsonConvert(String data){
        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(data);
         return  json.getAsJsonObject("Response").getAsJsonArray("NewsArticles").get(0);

    }

    private void closeConnection(){
        this.connection.disconnect();
    }


    @Override
    public void run() {
        String link = "";
        do {
            try {
                try {
                    int code = createConnection();
                    if (code == 200){
                        JsonElement result =  takeData();
                        String article = result.getAsJsonObject().get("Link").getAsString();
                        if (link.isBlank() || link.isEmpty()){
                            link = article ;
                        }else if(!link.equals(article)){
                            EmbedBuilder builder = new EmbedBuilder();
                            builder.setColor(Color.BLUE);
                            builder.setTitle( new String(result.getAsJsonObject().get("Title").getAsString().getBytes(), StandardCharsets.UTF_8) ,String.format("https://www.bungie.net%s", article));
                            builder.setDescription( new String(result.getAsJsonObject().get("Description").getAsString().getBytes(), StandardCharsets.UTF_8) );
                            builder.setImage(result.getAsJsonObject().get("ImagePath").getAsString());
                            newsletterTwab.forEach((key, value) -> {
                                jda.getTextChannelById(value).sendMessage("").addEmbeds(builder.build()).queue();
                            });
                            builder.clear();
                        }
                    }
                    closeConnection();
                    Thread.sleep(20000);
                } catch (IOException e) {
                    Thread.sleep(20000);
                } catch (Exception e){
                    Thread.sleep(60000);
                }
            } catch (InterruptedException ex) {
                System.err.println("[ERROR] thread sleep");
            }
        }while (this.isRunning);


    }
}
