package event;

import logger.LogLevel;
import logger.Logger;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class HelloPrint extends ListenerAdapter {

    @Override
    public void onGenericEvent(@NotNull GenericEvent event) {
        if (event instanceof ReadyEvent)
            Logger.log(LogLevel.INFORMATION, "API is ready!");
    }
}
