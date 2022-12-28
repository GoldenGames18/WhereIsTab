import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import repository.JsonRepository;
import repository.TomlRepository;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import slashCommand.SlashCommandPing;
import slashCommand.SlashCommandSubscribeNewsletterTwab;
import slashCommand.SlashCommandUnSubscribeNewsletterTwab;
import src.SearchArticles;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

public class App {

    public static void main(String[] args) {

        //Load api key and discord token
        TomlRepository repository = new TomlRepository();

        //Register to the newsletter
        ConcurrentHashMap<String, String> newsletterTwab = new ConcurrentHashMap<>();

        SearchArticles searchArticles = new SearchArticles(newsletterTwab, repository.getApiKey());
        try {

            //Load json file with serveur is subscribe
            JsonRepository jsonRepository = new JsonRepository(System.getProperty("user.dir")+"\\resources\\"+ repository.getJsonName());
            newsletterTwab.putAll(  jsonRepository.loadSubscribel(newsletterTwab));


            //Starting the bot with JDA
            JDA jda = JDABuilder.createDefault(repository.getToken())
                    .setActivity(Activity.playing("Where is Twab ?"))
                    .addEventListeners(new SlashCommandPing(),
                            new SlashCommandSubscribeNewsletterTwab(newsletterTwab, jsonRepository),
                            new SlashCommandUnSubscribeNewsletterTwab(newsletterTwab, jsonRepository))
                    .build().awaitReady();
            searchArticles.setJda(jda);
            new Thread(searchArticles).start();

            //Display of commands for each server
            for (Guild server : jda.getGuilds()) {
                server.updateCommands().addCommands(
                        Commands.slash("ping", "Ping the bot !"),
                        Commands.slash("subscribe", "Subscribe the server to the newsletter to read the latest articles from Bungie")
                                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)),
                        Commands.slash("unsubscribe", "Unsubscribe the server from the Bungie newsletter")
                                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR))
                ).queue();

            }



        } catch (InterruptedException e) {
            System.err.println("[ERROR] : The bot is stop");
            searchArticles.stop();
        } catch (Exception e) {
            System.err.println("[ERROR]");
            searchArticles.stop();
        }


    }
}
