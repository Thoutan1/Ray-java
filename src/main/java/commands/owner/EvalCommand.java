package commands.owner;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import features.Builder.ErrorEmbed;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EvalCommand extends Command {

    public EvalCommand() {
        this.name = "eval";
        this.help = "evaluates Nashorn code";
        this.arguments = "<Eval code here>";
        this.ownerCommand = true;
        this.guildOnly = false;
    }
    @Override
    protected void execute(CommandEvent event) {
        String message1 = event.getArgs();
        if (message1 != null && !message1.equals("")) {
            ScriptEngine se = new ScriptEngineManager().getEngineByName("Nashorn");
            se.put("event", event);
            se.put("jda", event.getJDA());
            if (event.getGuild() != null)
                se.put("guild", event.getGuild());
            se.put("channel", event.getChannel());
            se.put("message", event.getMessage());
            se.put("random", new Random());
            se.put("thread", ThreadLocalRandom.current());
            try {
                User author = event.getAuthor();
                if (event.isFromType(ChannelType.TEXT)) {
                    MessageEmbed embed = new EmbedBuilder()
                            .setAuthor(author.getName() + "#" + author.getDiscriminator(), null, author.getEffectiveAvatarUrl())
                            .setColor(event.getSelfMember().getColor())
                            .setDescription(event.getClient().getSuccess() + "Output: \n```\n" + se.eval(event.getArgs()) + "\n```")
                            .build();
                    event.reply(embed);
                } else {
                    MessageEmbed embed = new EmbedBuilder()
                            .setAuthor(author.getName() + "#" + author.getDiscriminator(), null, author.getEffectiveAvatarUrl())
                            .setColor(Color.GREEN)
                            .setDescription(event.getClient().getSuccess() + "Output: \n```\n" + se.eval(event.getArgs()) + "\n```")
                            .build();
                    event.reply(embed);
                }
            } catch (Exception e) {
                User author = event.getAuthor();
                if (event.isFromType(ChannelType.TEXT)) {
                    MessageEmbed embed = new EmbedBuilder()
                            .setAuthor(author.getName() + "#" + author.getDiscriminator(), null, author.getEffectiveAvatarUrl())
                            .setColor(event.getSelfMember().getColor())
                            .setDescription(event.getClient().getError() + " | An exception was thrown: \n```\n" + e.getMessage() + " \n```")
                            .build();
                    event.reply(embed);
                } else {
                    MessageEmbed embed = new EmbedBuilder()
                            .setAuthor(author.getName() + "#" + author.getDiscriminator(), null, author.getEffectiveAvatarUrl())
                            .setColor(Color.RED)
                            .setDescription(event.getClient().getError() + " | An exception was thrown: \n```\n" + e.getMessage() + " \n```")
                            .build();
                    event.reply(embed);
                }

            }
        } else {
            MessageEmbed ErrorMessage = ErrorEmbed.ErrorBuild("Upss Error", event.getSelfUser().getDefaultAvatarUrl(), this.getArguments());
            event.reply(ErrorMessage);
        }
    }
}
