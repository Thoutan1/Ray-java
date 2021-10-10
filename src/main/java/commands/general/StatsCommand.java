package commands.general;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.time.OffsetDateTime;

public class StatsCommand extends Command {
    private final OffsetDateTime start = OffsetDateTime.now();
    public StatsCommand() {
        this.name = "stats";
        this.help = "shows some statistics about the bot";
        this.guildOnly = true;
    }
    @Override
    protected void execute(CommandEvent event) {
        long totalMb = Runtime.getRuntime().totalMemory() / (1024 * 1024);
        long usedMb = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);
        String preFormattedTime = start.getDayOfMonth() + "." + start.getMonthValue() + "." + start.getYear() + " " + start.getHour() + ":" + start.getMinute() + ":" + start.getSecond() + " " + start.getOffset().toString();
        MessageEmbed embed = new EmbedBuilder()
                .setThumbnail(event.getSelfUser().getEffectiveAvatarUrl())
                .setColor(Color.GREEN)
        .addField("Last Startup", preFormattedTime, true)
        .addField("Users", String.valueOf(event.getJDA().getUsers().size()), true)
        .addField("TextChannels", String.valueOf(event.getJDA().getTextChannels().size()), true)
        .addField("PrivateChannels", String.valueOf(event.getJDA().getPrivateChannels().size()), true)
        .addField("VoiceChannels", String.valueOf(event.getJDA().getVoiceChannels().size()), true)
        .addField("Guilds", String.valueOf(event.getJDA().getGuilds().size()), true)
        .addField("Memory", usedMb + "Mb / " + totalMb + "Mb", true)
        .addField("Response Total", String.valueOf(event.getJDA().getResponseTotal()), true)
                .build();
        event.reply(embed);
    }
}
