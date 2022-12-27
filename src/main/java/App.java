import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import repository.TomlRepository;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import slashCommand.SlashCommandPing;
import slashCommand.SlashCommandSubscribeNewsletterTwab;
import slashCommand.SlashCommandUnSubscribeNewsletterTwab;

import java.util.concurrent.ConcurrentHashMap;

public class App {

    public static void main(String[] args)  {

        //Register to the newsletter
        ConcurrentHashMap<String, String> newsletterTwab = new ConcurrentHashMap<>();



        try{
            //Load api key and discord token
            TomlRepository repository = new TomlRepository();

            //Starting the bot with JDA
            JDA jda = JDABuilder.createDefault(repository.getToken())
                    .setActivity(Activity.playing("Where is Twab ?"))
                    .addEventListeners(new SlashCommandPing(),
                            new SlashCommandSubscribeNewsletterTwab(newsletterTwab),
                            new SlashCommandUnSubscribeNewsletterTwab(newsletterTwab))
                    .build().awaitReady();


            //Display of commands for each server
            for ( Guild server : jda.getGuilds()){
                server.updateCommands().addCommands(
                        Commands.slash("ping", "Ping the bot !"),
                        Commands.slash("subscribe", "Subscribe to the newsletter to read the latest articles from Bungie")
                                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)),
                        Commands.slash("unsubscribe", "Unsubscribe from the Bungie newsletter" )
                                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))
                ).queue();

            }

        } catch (InterruptedException e) {
            System.err.println("[ERROR] : The bot is stop");
        }catch (Exception e){
            System.err.println("[ERROR]");
        }


    }
}
