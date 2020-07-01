package net.dzikoysk.wildskript.collections.functions;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Function {

    private static final List<FunctionEvent> functions = new ArrayList<>();

    @NotNull
    public static FunctionEvent call(String name, Object[] args) {
        FunctionEvent custom = new FunctionEvent(name, args);
        Bukkit.getServer().getPluginManager().callEvent(custom);
        return custom;
    }

    protected static void addFunction(FunctionEvent f) {
        functions.add(f);
    }

    protected static void removeFunction(FunctionEvent f) {
        functions.remove(f);
    }

    public static List<FunctionEvent> getFunctions() {
        return functions;
    }
}
