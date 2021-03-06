package net.dzikoysk.wildskript.expressions.files;

import ch.njol.skript.doc.NoDoc;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Event;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

@NoDoc
public class ExprYamlConfigurationSection extends SimpleExpression<String> {
    private Expression<String> key;
    private Expression<String> file;

    protected String[] get(Event event) {
        String k = this.key.getSingle(event);
        String f = this.file.getSingle(event);
        if ((k == null) || (f == null)) return null;
        File file = new File(f.replaceAll("/", Matcher.quoteReplacement(File.separator)));
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException ignored) {
            }
        }
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection cs = yml.getConfigurationSection(k);
        if (cs == null) return null;
        ArrayList<String> list = new ArrayList<>(cs.getKeys(false));
        String[] s = new String[list.size()];
        return list.toArray(s);
    }

    public boolean isSingle() {
        return true;
    }

    public Class<? extends String> getReturnType() {
        return String.class;
    }

    public String toString(Event event, boolean b) {
        return this.getClass().getName();
    }

    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.key = (Expression<String>) expressions[0];
        this.file = (Expression<String>) expressions[1];
        return true;
    }
}




