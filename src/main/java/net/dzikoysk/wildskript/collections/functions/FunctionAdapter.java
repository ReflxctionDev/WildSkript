package net.dzikoysk.wildskript.collections.functions;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import org.bukkit.event.Event;

public class FunctionAdapter extends SkriptEvent {

    private String name;

    public boolean check(Event event) {
        if (event instanceof FunctionEvent) return ((FunctionEvent) event).getName().equals(name);
        return false;
    }

    public String toString(Event event, boolean bool) {
        return this.getClass().getName();
    }

    public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parser) {
        this.name = ((String) args[0].getSingle());
        return this.name != null;
    }
}
