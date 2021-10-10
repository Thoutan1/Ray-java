package features.Builder;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class ErrorEmbed {

    public static MessageEmbed ErrorBuild(String title, String thumnail, String description) {
        return new EmbedBuilder()
                .setColor(Color.RED)
                .setTitle(title)
                .setDescription(description)
                .setFooter("Error!")
                .setThumbnail(thumnail)
                .build();
    }
}
