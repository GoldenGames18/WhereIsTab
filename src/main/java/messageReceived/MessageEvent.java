package messageReceived;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageEvent extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println(event.getMessage().getContentDisplay().matches("[XxuURr]"));
        if(event.getMessage().getContentDisplay().matches("[Xx][uU][Rr]")){
            event.getMessage().reply("https://whereisxur.com/").queue();
        }else if(event.getMessage().getContentDisplay().matches("[lL][oO][sS][Tt] [Ss][Ee][Cc][Tt][Oo][Rr]")){
            event.getMessage().reply("https://lostsector.report/").queue();
        }
    }
}
