package net.dzikoysk.wildskript.collections.packet;

public class Parser {

    public static Object parse(Class<?> targetClass, Object o) {
        if (targetClass == int.class) {
            if ((o instanceof Number)) return ((Number) o).intValue();
            if ((o instanceof String)) return Integer.valueOf(o.toString());
            return o;
        }
        if (targetClass == short.class) {
            if ((o instanceof Number)) return ((Number) o).shortValue();
            if ((o instanceof String)) return Short.valueOf(o.toString());
            return o;
        }
        if (targetClass == double.class) {
            if ((o instanceof Number)) return ((Number) o).doubleValue();
            if ((o instanceof String)) return Double.valueOf(o.toString());
            return o;
        }
        if (targetClass == float.class) {
            if ((o instanceof Number)) return ((Number) o).floatValue();
            if ((o instanceof String)) return Double.valueOf(o.toString());
            return o;
        }
        if (targetClass == long.class) {
            if ((o instanceof Number)) return ((Number) o).longValue();
            if ((o instanceof String)) return Double.valueOf(o.toString());
            return o;
        }
        return o;
    }

}
