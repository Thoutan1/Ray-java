import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import commands.general.PingCommand;
import event.HelloPrint;
import event.MessageReceived;
import logger.LogLevel;
import logger.Logger;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import util.PropertiesUtil;

import javax.security.auth.login.LoginException;
import java.util.Properties;
import java.io.*;

public class Bot extends ListenerAdapter
{
    public static void main(String[] args) throws LoginException, IOException {
        Properties config = PropertiesUtil.getProperties();

        if(config == null) {
            Logger.log(LogLevel.DEBUG, "Config File is not found Please wait I create it");
            return;
        }
        if(config.getProperty("botToken").equals("BotTokenHEre")) {
            Logger.log(LogLevel.ERROR,"Please Replace bot token!");
            return;
        }
        String token = config.getProperty("botToken");
        String ownerId = config.getProperty("ownerId");
        String prefix = config.getProperty("Prefix");

        EventWaiter waiter = new EventWaiter();
        CommandClientBuilder client = new CommandClientBuilder()
                .setPrefix(prefix)
                .setStatus(OnlineStatus.ONLINE)
                .setOwnerId(ownerId)
                .setActivity(Activity.playing("!help"));

        client.addCommands(
                // command to show information about the bot
               new PingCommand());

        JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new Bot())
                .addEventListeners(new MessageReceived())
                .addEventListeners(waiter, client.build())
                .addEventListeners(new HelloPrint())
                .build();
    }
}
