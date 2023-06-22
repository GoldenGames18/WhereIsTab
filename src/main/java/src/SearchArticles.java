package src;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

public class SearchArticles implements Runnable{

    private ConcurrentHashMap<String, String> newsletterTwab;
    private JDA jda;
    private boolean isRunning;
    private String apiKey;
    private URL url;

    public SearchArticles(ConcurrentHashMap<String, String> newsletterTwab, String apiKey) {
        this.newsletterTwab = newsletterTwab;
        this.apiKey = apiKey;
        try {
            this.url =  new URL("https://www.bungie.net/platform/Content/Rss/NewsArticles/0");
        } catch (MalformedURLException e){
            this.isRunning = false;
        }
    }
    public void setJda(JDA jda) {
        this.jda = jda;
    }

    public void stop(){
        this.isRunning = false;
    }

    private JsonElement jsonConvert(String data){
        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(data);
         return  json.getAsJsonObject("Response").getAsJsonArray("NewsArticles").get(0);

    }




    @Override
    public void run() {
        String link = "";
        try{
            do{
                try(final Fetch connection = new Fetch(this.url, this.apiKey) ) {
                    connection.createConnection("GET");
                    if (connection.statusCode() == 200){
                        JsonElement result = jsonConvert(connection.extractData());
                        String article = result.getAsJsonObject().get("Link").getAsString();
                        if (link.isBlank() || link.isEmpty()){
                            link = article ;
                        }else if(!link.equals(article)){
                            EmbedBuilder builder = new EmbedBuilder();
                            builder.setColor(Color.BLUE);
                            builder.setTitle( new String(result.getAsJsonObject().get("Title").getAsString().getBytes(), StandardCharsets.UTF_8) ,String.format("https://www.bungie.net%s", article));
                            builder.setDescription( new String(result.getAsJsonObject().get("Description").getAsString().getBytes(), StandardCharsets.UTF_8) );
                            builder.setImage(result.getAsJsonObject().get("ImagePath").getAsString());
                            this.newsletterTwab.forEach((key, value) -> {
                                this.jda.getTextChannelById(value).sendMessage("").addEmbeds(builder.build()).queue();
                            });
                            builder.clear();
                        }
                    }else{
                        System.err.printf("[Error] %s on  %s \n", connection.statusCode(), connection.getUrl());
                    }
                } catch (IOException e) {
                    Thread.sleep(20000);
                }catch (Exception e){
                    Thread.sleep(60000);
                }
                Thread.sleep(20000);
            }while (this.isRunning);
        }catch (InterruptedException e){
            System.err.println("[ERROR] thread sleep on SearchArticle");
        }


    }
}
