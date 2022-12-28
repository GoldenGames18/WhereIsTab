package slashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import repository.JsonRepository;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class SlashCommandSubscribeNewsletterTwab extends ListenerAdapter {

    private ConcurrentHashMap<String, String> newsletterTwab;
    private JsonRepository jsonRepository;
    public SlashCommandSubscribeNewsletterTwab(ConcurrentHashMap<String, String> newsletterTwab, JsonRepository jsonRepository){
        this.newsletterTwab = newsletterTwab;
        this.jsonRepository = jsonRepository;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("subscribe")){
            event.deferReply().queue();
            try{
                if (this.newsletterTwab.containsKey(event.getGuild().getId())){
                    event.getHook().sendMessage("You are already registered").setEphemeral(true).queue();
                }else{
                    this.newsletterTwab.put(Objects.requireNonNull(event.getGuild()).getId(), event.getChannel().getId());
                    if (this.jsonRepository.updateSubscribel(this.newsletterTwab)){
                        event.getHook().sendMessage("Your server are subscribe to the newsletter. All articles will be posted here").setEphemeral(true).queue();
                    }else{
                        event.getHook().sendMessage("An error has occurred").setEphemeral(true).queue();
                    }

                }
            }catch (NullPointerException e){
                event.getHook().sendMessage("An error has occurred").setEphemeral(true).queue();
            }
        }
    }
}
