import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import repository.JsonRepository;
import repository.TomlRepository;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import slashCommand.SlashCommandAllWishes;
import slashCommand.SlashCommandPing;
import slashCommand.SlashCommandSubscribeNewsletterTwab;
import slashCommand.SlashCommandUnSubscribeNewsletterTwab;
import src.SearchArticles;
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
                            new SlashCommandUnSubscribeNewsletterTwab(newsletterTwab, jsonRepository),
                            new SlashCommandAllWishes())
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
                                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)),
                        Commands.slash("wish","All Wishes for the Wall of Wishes")
                                .addSubcommands(new SubcommandData("key", "Wish 1 grants an Ethereal key."))
                                .addSubcommands(new SubcommandData("chest", "Wish 2 causes a chest to spawn between the Morgeth, the Spirekeeper."))
                                .addSubcommands(new SubcommandData("emblem", "Wish 3 unlocks an Emblem."))
                                .addSubcommands(new SubcommandData("shuro-chi", "Wish 4 teleports entire fireteam to Shuro Chi."))
                                .addSubcommands(new SubcommandData("morgeth", "Wish 5 teleports entire fireteam to Morgeth."))
                                .addSubcommands(new SubcommandData("vault", "Wish 6 teleports entire fireteam to the vault encounter."))
                                .addSubcommands(new SubcommandData("riven", "Wish 7 teleports entire fireteam to rive of thousand Voices."))
                                .addSubcommands(new SubcommandData("song", "Wish 8 will play the song, Hope for the Future."))
                                .addSubcommands(new SubcommandData("failsafe", "Wish 9 activates a piece of dialogue from Failsafe, who then speaks throughout the raid."))
                                .addSubcommands(new SubcommandData("drifter", "Wish 10 adds Drifter dialogue to the raid."))
                                .addSubcommands(new SubcommandData("headshot", "Wish 11 adds an explosive effect to headshot kills."))
                                .addSubcommands(new SubcommandData("head", "Wish 12 adds an effect around the player's head."))
                                .addSubcommands(new SubcommandData("petras-run", "Wish 13 enables Extinguish where if one player dies the entire fireteam goes to Orbit."))
                                .addSubcommands(new SubcommandData("eggs", "Wish 14 spawns several Corrupted Eggs throughout the raid."))
                ).queue();
            }
        } catch (InterruptedException e) {
            System.err.println("[ERROR] : The bot is stop");
            searchArticles.stop();
        } catch (Exception e) {
            searchArticles.stop();
            e.printStackTrace();
        }


    }
}
