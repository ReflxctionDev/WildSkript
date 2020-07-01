package net.dzikoysk.wildskript.objects.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Objects;

public class Scoreboards {

    public static ScoreboardManager manager;
    public static Scoreboard sb;

    static {
        manager = Bukkit.getScoreboardManager();
        sb = Objects.requireNonNull(manager).getNewScoreboard();
    }

    public static ScoreboardManager getScoreboardManager() {
        return manager;
    }

    public static Scoreboard getScoreboard() {
        return sb;
    }

}
