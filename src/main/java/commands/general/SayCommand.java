package commands.general;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import features.Builder.ErrorEmbed;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class SayCommand extends Command {
   public SayCommand(){
       this.name = "say";
       this.arguments = "<Message>";
       this.hidden = true;
       this.guildOnly = true;
   }
   @Override
    protected void execute(CommandEvent event){
       String message = event.getArgs();
       if (message != null && !message.equals("")) {
           Message referencedMessage = event.getMessage().getReferencedMessage();
           if (referencedMessage != null) {
               referencedMessage.reply(message).queue();
           } else {
               event.reply(message);
           }
       } else {
           MessageEmbed ErrorMessage = ErrorEmbed.ErrorBuild("Upss Error", event.getSelfUser().getDefaultAvatarUrl(), "Please include a `<message>` when using the `say` command.");
           event.reply(ErrorMessage);
       }
   }
}
