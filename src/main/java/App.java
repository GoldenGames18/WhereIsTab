import repository.TomlRepository;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class App {

    public static void main(String[] args)  {

        try{
            TomlRepository repository = new TomlRepository();

            JDA jda = JDABuilder.createDefault(repository.getToken())
                    .setActivity(Activity.playing("Where is Twab ?"))
                    .addEventListeners()
                    .build().awaitReady();

        } catch (InterruptedException e) {
            System.err.println("[ERROR] : The bot is stop");
        }


    }
}
