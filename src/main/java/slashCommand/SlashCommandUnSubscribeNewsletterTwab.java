package slashCommand;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.ConcurrentHashMap;

public class SlashCommandUnSubscribeNewsletterTwab extends ListenerAdapter {

    private ConcurrentHashMap<String, String> newsletterTwab;



    public SlashCommandUnSubscribeNewsletterTwab(ConcurrentHashMap<String, String> newsletterTwab){
        this.newsletterTwab = newsletterTwab;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (event.getName().equals("unsubscribe")) {
            event.deferReply().queue();
            try {
                if (this.newsletterTwab.containsValue(event.getChannel().getId())){
                    if (this.newsletterTwab.remove(event.getGuild().getId(), event.getChannel().getId()))
                        event.getHook().sendMessage("You are unsubscribed from the newsletter").setEphemeral(true).queue();
                    else
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
