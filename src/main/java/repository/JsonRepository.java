package repository;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

public class JsonRepository {

    private String path;

    public JsonRepository(String path){
        this.path = path;
    }

    public ConcurrentHashMap<String, String>  loadSubscribel( ConcurrentHashMap<String, String> newsletterTwab) throws FileNotFoundException {
        return new Gson().fromJson(new FileReader(path), newsletterTwab.getClass());
    }

    public boolean updateSubscribel( ConcurrentHashMap<String, String> newsletterTwab){
        try (FileWriter fw = new FileWriter(path)){
            fw.write(new Gson().toJson(newsletterTwab));
            fw.flush();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
