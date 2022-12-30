package slashCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.nio.charset.StandardCharsets;

public class SlashCommandAllWishes extends ListenerAdapter {


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("wish")){
            event.deferReply().queue();
            switch (event.getSubcommandName()){
                case "key":
                    event.getHook().sendMessage("").addEmbeds(this.createEmbed("Wish 1","Get Ethereal Key.","https://d1lss44hh2trtw.cloudfront.net/resize?type=webp&url=https%3A%2F%2Fshacknews-www.s3.amazonaws.com%2Fassets%2Feditorial%2F2018%2F09%2Fdestiny-2-wish-1-first-wish.jpg&width=986&sign=TVoNNScsX1PCBrhg5B6HS94a7h5yY_i27B9exrDu5io")).setEphemeral(true).queue();
                    break;
                case "chest":
                    event.getHook().sendMessage("").addEmbeds(this.createEmbed("Wish 2","a chest to spawn between the Morgeth, the Spirekeeper fight and the Vault that can only be opened with a Glittering Key.","https://d1lss44hh2trtw.cloudfront.net/resize?type=webp&url=https%3A%2F%2Fshacknews-www.s3.amazonaws.com%2Fassets%2Feditorial%2F2018%2F09%2Fdestiny-2-wish-2-second-wish.jpg&width=986&sign=9_JRunUBlJ79usqmdIu-QAB804UIGAVMeg19TkxsGl4")).setEphemeral(true).queue();
                  case "emblem":
                      event.getHook().sendMessage("").addEmbeds(this.createEmbed("Wish 3","Unlocks an Emblem.","https://d1lss44hh2trtw.cloudfront.net/resize?type=webp&url=https%3A%2F%2Fshacknews-www.s3.amazonaws.com%2Fassets%2Feditorial%2F2018%2F09%2Fdestiny-2-wish-3-third-wish.jpg&width=986&sign=Ft3fQ85BIKjVhnEWuZNirM9zvZCM6B07GIcWUgz7_So")).setEphemeral(true).queue();
                       break;
                case "shuro-chi":
                    event.getHook().sendMessage("").addEmbeds(this.createEmbed("Wish 4","Will immediately wipe the Fireteam and teleport them to the beginning of the fight against Shuro Chi, the Corrupted.","https://d1lss44hh2trtw.cloudfront.net/resize?type=webp&url=https%3A%2F%2Fshacknews-www.s3.amazonaws.com%2Fassets%2Feditorial%2F2018%2F09%2Fdestiny-2-wish-4-fourth-wish.jpg&width=986&sign=uhG8WXFco1c59NLIg1UPJw3SH-xLIJ1pvCKjskKKBnU")).setEphemeral(true).queue();
                    break;
                case "morgeth":
                    event.getHook().sendMessage("").addEmbeds(this.createEmbed("Wish 5","Will immediately wipe the Fireteam and teleport them to the beginning of the Morgeth, the Spirekeeper, fight.","https://d1lss44hh2trtw.cloudfront.net/resize?type=webp&url=https%3A%2F%2Fshacknews-www.s3.amazonaws.com%2Fassets%2Feditorial%2F2018%2F09%2Fdestiny-2-wish-5-fifth-wish.jpg&width=986&sign=1bopdN1cmcff467e60Qb0BtEtp16RPMKQIX3zLRTcUg")).setEphemeral(true).queue();
                    break;
                case "vault":
                    event.getHook().sendMessage("").addEmbeds(this.createEmbed("Wish 6","Will immediately wipe the Fireteam and teleport them to the beginning of the Vault encounter.","https://d1lss44hh2trtw.cloudfront.net/resize?type=webp&url=https%3A%2F%2Fshacknews-www.s3.amazonaws.com%2Fassets%2Feditorial%2F2018%2F09%2Fdestiny-2-wish-6-sixth-wish.jpg&width=986&sign=2mXiQ6pbnOWj3NgxEh-4dCsR2BRIyzeaGs7u_KxABhg")).setEphemeral(true).queue();
                    break;
                case "riven":
                    event.getHook().sendMessage("").addEmbeds(this.createEmbed("Wish 7","Will immediately wipe the Fireteam and teleport them to the beginning of the Riven of a Thousand Voices encounter.","https://d1lss44hh2trtw.cloudfront.net/resize?type=webp&url=https%3A%2F%2Fshacknews-www.s3.amazonaws.com%2Fassets%2Feditorial%2F2018%2F09%2Fdestiny-2-wish-7-seventh-wish.jpg&width=986&sign=_EiIyhavEOmXElsE9BCayjeSAuIiy_6quRnjXtDQDlI")).setEphemeral(true).queue();
                    break;
                case "song":
                    event.getHook().sendMessage("").addEmbeds(this.createEmbed("Wish 8","Will play the song, Hope for the Future.","https://d1lss44hh2trtw.cloudfront.net/resize?type=webp&url=https%3A%2F%2Fshacknews-www.s3.amazonaws.com%2Fassets%2Feditorial%2F2018%2F09%2Fdestiny-2-wish-8-eighth-wish.jpg&width=986&sign=cFNZ1J03oiVjR8ej_Ux3Eu3z4TzalQnH5mXjHf2kv7s")).setEphemeral(true).queue();
                   break;
                case "failsafe":
                    event.getHook().sendMessage("").addEmbeds(this.createEmbed("Wish 9","Activates a piece of dialogue from Failsafe, who then speaks throughout the raid.","https://d1lss44hh2trtw.cloudfront.net/resize?type=webp&url=https%3A%2F%2Fshacknews-www.s3.amazonaws.com%2Fassets%2Feditorial%2F2018%2F09%2Fdestiny-2-wish-9-ninth-wish.jpg&width=986&sign=ApVF8wuVSmH2OSu0Vt6uJQ2ITJN-4wo-fkPdiV-ando")).setEphemeral(true).queue();
                   break;
                case "drifter":
                    event.getHook().sendMessage("").addEmbeds(this.createEmbed("Wish 10","Adds Drifter dialogue to the raid.","https://d1lss44hh2trtw.cloudfront.net/resize?type=webp&url=https%3A%2F%2Fshacknews-www.s3.amazonaws.com%2Fassets%2Feditorial%2F2018%2F10%2Fdestiny-2-wish-10-tenth-wish.jpg&width=986&sign=z9E62IU8YBZ4YKT-EDqf3aYzwdzjgRN557Tz043NRNU")).setEphemeral(true).queue();
                   break;
                case "headshot":
                    event.getHook().sendMessage("").addEmbeds(this.createEmbed("Wish 11","Adds an explosive effect to headshot kills, similar to the Grunt Birthday Party skull from Halo 2.","https://d1lss44hh2trtw.cloudfront.net/resize?type=webp&url=https%3A%2F%2Fshacknews-www.s3.amazonaws.com%2Fassets%2Feditorial%2F2018%2F09%2Fdestiny-2-wish-11-eleventh-wish.jpg&width=986&sign=a-Zk1CXqVgTYrMM0K7Xpz2nuQaLqWyfrfewuqNEFYrI")).setEphemeral(true).queue();
                    break;
                case "head":
                    event.getHook().sendMessage("").addEmbeds(this.createEmbed("Wish 12","Adds an effect around the player's head.","https://d1lss44hh2trtw.cloudfront.net/resize?type=webp&url=https%3A%2F%2Fshacknews-www.s3.amazonaws.com%2Fassets%2Feditorial%2F2018%2F09%2Fdestiny-2-wish-12-twelfth-wish.jpg&width=986&sign=mvVz1PLxokW9Q3ZSdOn9xINSs7yEq4nLIAS5oo8aMqc")).setEphemeral(true).queue();
                    break;
                case "petras-run":
                    event.getHook().sendMessage("").addEmbeds(this.createEmbed("Wish 13","Enables Extinguish, where if one player dies, the entire Fireteam goes to Orbit and the raid will reset.","https://d1lss44hh2trtw.cloudfront.net/resize?type=webp&url=https%3A%2F%2Fshacknews-www.s3.amazonaws.com%2Fassets%2Feditorial%2F2018%2F10%2Fdestiny-2-wish-13-thirteenth-wish.jpg&width=986&sign=7O0cDJA1oJsl7InnuARiWhQgAuMAor0iMisTMAjFJqo")).setEphemeral(true).queue();
                    break;
                case "eggs":
                    event.getHook().sendMessage("").addEmbeds(this.createEmbed("Wish 14","Spawns several Corrupted Eggs throughout the raid.","https://d1lss44hh2trtw.cloudfront.net/resize?type=webp&url=https%3A%2F%2Fshacknews-www.s3.amazonaws.com%2Fassets%2Feditorial%2F2018%2F09%2Fdestiny-2-wish-14-fourteenth-wish.jpg&width=986&sign=nsQmGqM2LtJVJZjFqh9VI0XIrJv37Utrg3YW2tWYt_A")).setEphemeral(true).queue();
                    break;
            }
        }
    }


    private MessageEmbed createEmbed(String title, String description, String link){
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(new Color(143,140,230));
        builder.setTitle(title);
        builder.setDescription(description);
        builder.setImage(link);
        return builder.build();
    }

}
