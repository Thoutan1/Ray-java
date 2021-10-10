import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import commands.general.PingCommand;
import event.HelloPrint;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;



public class Bot extends ListenerAdapter
{
    public static void main(String[] args) throws LoginException
    {
        Dotenv dotenv = Dotenv.load();
        EventWaiter waiter = new EventWaiter();
        CommandClientBuilder client = new CommandClientBuilder()
                .setPrefix(dotenv.get("DISCORD_PREFIX"))
                .setStatus(OnlineStatus.ONLINE)
                .setOwnerId("776458781239410698")
                .setActivity(Activity.playing("!help"));

        client.addCommands(
                // command to show information about the bot
               new PingCommand());

        JDABuilder.createLight(dotenv.get("DISCORD_TOKEN"), GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new Bot())
                .addEventListeners(waiter, client.build())
                .addEventListeners(new HelloPrint())
                .build();
    }
}
