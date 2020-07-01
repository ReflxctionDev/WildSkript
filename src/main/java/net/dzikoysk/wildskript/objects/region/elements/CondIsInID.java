package net.dzikoysk.wildskript.objects.region.elements;

import ch.njol.skript.doc.NoDoc;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.dzikoysk.wildskript.objects.region.RegionsUtils;
import org.bukkit.Location;
import org.bukkit.event.Event;

@NoDoc
public class CondIsInID extends Condition {
    private Expression<Location> location;
    private Expression<String> id;

    public boolean check(Event event) {
        Location loc = this.location.getSingle(event);
        String id = this.id.getSingle(event);

        if (RegionsUtils.get(id).isIn(loc)) {
            return !isNegated();
        }
        return isNegated();
    }

    public String toString(Event event, boolean b) {
        return "";
    }

    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] e, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.location = e[0].getConvertedExpression(new Class[]{Object.class});
        this.id = e[1].getConvertedExpression(new Class[]{Object.class});
        setNegated(i == 1);
        return true;
    }
}
