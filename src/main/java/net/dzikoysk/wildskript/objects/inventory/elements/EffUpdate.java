package net.dzikoysk.wildskript.objects.inventory.elements;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.dzikoysk.wildskript.objects.inventory.Inventories;
import org.bukkit.event.Event;

public class EffUpdate extends Effect {

    private Expression<String> name;

    protected void execute(Event event) {

        String name = this.name.getSingle(event);
        if (name == null) return;

        Inventories.get(name).updateInv();

    }

    public String toString(Event event, boolean bool) {
        return "[Inventory] Register Items";
    }

    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.name = (Expression<String>) expressions[0];
        return true;
    }
}









