package Repository;


import com.moandjiezana.toml.Toml;

import java.io.File;

public class TomlRepository {

    public Toml config;

    public TomlRepository(){
        config = new Toml().read( new File(System.getProperty("user.dir"),"config.toml" ));
    }

    public String getToken(){
        return config.getString("bot.token");
    }

    public String getApiKey(){return config.getString("bot.ApiKey");}


}
