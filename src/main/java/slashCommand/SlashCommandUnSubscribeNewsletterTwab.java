package slashCommand;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import repository.JsonRepository;

import java.util.concurrent.ConcurrentHashMap;

public class SlashCommandUnSubscribeNewsletterTwab extends ListenerAdapter {

    private ConcurrentHashMap<String, String> newsletterTwab;
    private JsonRepository jsonRepository;


    public SlashCommandUnSubscribeNewsletterTwab(ConcurrentHashMap<String, String> newsletterTwab, JsonRepository jsonRepository){
        this.newsletterTwab = newsletterTwab;
        this.jsonRepository = jsonRepository;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (event.getName().equals("unsubscribe")) {
            event.deferReply().queue();
            try {
                if (this.newsletterTwab.containsKey(event.getGuild().getId())){
                    String value = newsletterTwab.get(event.getGuild().getId());
                    if (this.newsletterTwab.remove(event.getGuild().getId(), value)) {
                        if (this.jsonRepository.updateSubscribel(newsletterTwab)){
                            event.getHook().sendMessage("Your server are unsubscribed from the newsletter").setEphemeral(true).queue();
                        }else{
                            event.getHook().sendMessage("An error has occurred").setEphemeral(true).queue();
                        }
                    }else
                        event.getHook().sendMessage("An error has occurred").setEphemeral(true).queue();
                }else{
                    event.getHook().sendMessage("You must be subscribed to be able to unsubscribe").setEphemeral(true).queue();
                }

            } catch (NullPointerException e) {
                event.getHook().sendMessage("An error has occurred").setEphemeral(true).queue();
            }
        }
    }
}
