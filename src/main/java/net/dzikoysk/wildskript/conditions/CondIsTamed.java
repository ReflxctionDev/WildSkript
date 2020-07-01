package net.dzikoysk.wildskript.conditions;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Tameable;
import org.bukkit.event.Event;

public class CondIsTamed extends Condition {

    private int matchedPattern;
    private Expression<Entity> e;

    public boolean check(Event event) {
        Entity e = this.e.getSingle(event);
        if (e == null) return false;
        if (e instanceof Tameable) {
            if (((Tameable) e).isTamed()) {
                return matchedPattern != 1;
            }
        }
        return matchedPattern == 1;
    }

    public String toString(Event event, boolean b) {
        return this.getClass().getName();
    }

    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.matchedPattern = matchedPattern;
        this.e = (Expression<Entity>) expressions[0];
        return true;
    }
}
