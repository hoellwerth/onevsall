package eu.baumistlustig.onevsall;

import eu.baumistlustig.onevsall.commands.*;
import eu.baumistlustig.onevsall.events.*;
import eu.baumistlustig.onevsall.utils.Round;
import eu.baumistlustig.onevsall.utils.Timer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class OnevsAll extends JavaPlugin {

    private static OnevsAll instance;

    private Timer timer;

    private Round round;

    private Start kitMenu;

    private getStartMenu getStartMenu;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {

        Bukkit.getLogger().info("§aOneVsAll Plugin wurde erfolgreich geladen.");

        Objects.requireNonNull(getCommand("timer")).setExecutor(new TimerCommand());
        Objects.requireNonNull(getCommand("start")).setExecutor(new Start());
        Objects.requireNonNull(getCommand("getstartmenu")).setExecutor(new getStartMenu());
        Objects.requireNonNull(getCommand("round")).setExecutor(new RoundCommand());
        Objects.requireNonNull(getCommand("reset")).setExecutor(new reset());

        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new connectionEvents(), this);
        pluginManager.registerEvents(new chatEvents(), this);
        pluginManager.registerEvents(new InventoryEvents(), this);
        pluginManager.registerEvents(new ItemClickEvent(), this);
        pluginManager.registerEvents(new roundEvents(), this);
        timer = new Timer(false, 0);

        round = new Round(false, 0);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static OnevsAll getInstance() {
        return instance;
    }

    public Timer getTimer()  {
        return timer;
    }

    public Round getRound() { return round; }

    public Start getKitMenu() { return kitMenu; }

    public getStartMenu getStartMenuItem() { return getStartMenu; }
}
