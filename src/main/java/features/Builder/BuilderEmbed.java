package features.Builder;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;


public class BuilderEmbed {
    public static MessageEmbed buildEmbed(String title, String description) {
        return new EmbedBuilder()
                .setColor(Color.GREEN)
                .setTitle(title)
                .setDescription(description)
                .setFooter("RAY BOT")
                .build();
    }
}
