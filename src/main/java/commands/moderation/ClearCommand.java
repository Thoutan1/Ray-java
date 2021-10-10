package commands.moderation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Message;

import java.util.List;

public class ClearCommand extends Command {
    public ClearCommand() {
        this.name = "clear";
        this.arguments = "<enum>";
    }
    @Override
    protected void execute(CommandEvent event) {
        event.getMessage();
            try {
                int messageAmount = Integer.parseInt(event.getArgs());
                List<Message> messages = event.getTextChannel().getHistory().retrievePast(messageAmount).complete();
                for (int i = 0; i < messageAmount; i++) {
                    event.getTextChannel().deleteMessageById(messages.get(i).getId()).queue();
                }
            } catch (NumberFormatException exception) {
                event.reply("No specified args");
            }
    }
}
