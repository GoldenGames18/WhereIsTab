package slashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class SlashCommandSubscribeNewsletterTwab extends ListenerAdapter {

    private ConcurrentHashMap<String, String> newsletterTwab;

    public SlashCommandSubscribeNewsletterTwab(ConcurrentHashMap<String, String> newsletterTwab){
        this.newsletterTwab = newsletterTwab;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("subscribe")){
            event.deferReply().queue();
            try{
                if (this.newsletterTwab.contains(event.getChannel().getId())){
                    event.getHook().sendMessage("Are you already registered").setEphemeral(true).queue();
                }else{
                    this.newsletterTwab.put(Objects.requireNonNull(event.getGuild()).getId(), event.getChannel().getId());
                    event.getHook().sendMessage("Your are subscribe to the newsletter. All articles will be posted here").setEphemeral(true).queue();
                }
            }catch (NullPointerException e){
                event.getHook().sendMessage("An error has occurred").setEphemeral(true).queue();
            }
        }
    }
}
