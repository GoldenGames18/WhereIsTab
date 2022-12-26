import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import repository.TomlRepository;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import slashCommand.SlashCommandPing;

public class App {

    public static void main(String[] args)  {

        try{
            //Load api key and discord token
            TomlRepository repository = new TomlRepository();

            //Starting the bot with JDA
            JDA jda = JDABuilder.createDefault(repository.getToken())
                    .setActivity(Activity.playing("Where is Twab ?"))
                    .addEventListeners(new SlashCommandPing())
                    .build().awaitReady();

            //Display of commands for each server
            for ( Guild server : jda.getGuilds()){
                server.updateCommands().addCommands(
                        Commands.slash("ping", "ping the bot !!")
                );
            }


        } catch (InterruptedException e) {
            System.err.println("[ERROR] : The bot is stop");
        }


    }
}
