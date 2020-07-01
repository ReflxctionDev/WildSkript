package net.dzikoysk.wildskript.objects.inventory.elements;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.dzikoysk.wildskript.objects.inventory.Inventories;
import org.bukkit.event.Event;

public class EffExecutor extends Effect {

    private Expression<String> name;
    private Expression<Number> slot;
    private Expression<String> exc;

    protected void execute(Event event) {

        String name = this.name.getSingle(event);
        Number slot = this.slot.getSingle(event);
        String exc = this.exc.getSingle(event);
        if (name == null || slot == null || exc == null) return;

        int i = slot.intValue();
        Inventories.get(name).setExecutor(i, exc);

    }

    public String toString(Event event, boolean bool) {
        return "[Inventory] Register Executor";
    }

    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.name = (Expression<String>) expressions[0];
        this.slot = (Expression<Number>) expressions[1];
        this.exc = (Expression<String>) expressions[2];
        return true;
    }
}









