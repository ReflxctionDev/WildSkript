package net.dzikoysk.wildskript.collections.packet;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class PacketList {

    private enum Packets {

        PACKET_PLAY_OUT_PLAYER_INFO(String.class, boolean.class, int.class),
        PACKET_PLAY_IN_KEEP_ALIVE();

        private Class<?>[] types;

        Packets(Class<?>... types) {
            this.types = types;
        }

        private Class<?>[] getTypes() {
            return types;
        }

        public static Packets get(String s) {
            for (Packets p : Packets.values()) if (s.equalsIgnoreCase(p.toString().replace("_", ""))) return p;
            return null;
        }
    }

    public static Object getPacket(Class<?> packetClass, Object[] values) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        String name = packetClass.getSimpleName();
        Class<?>[] types = Objects.requireNonNull(Packets.get(name), "Invalid packet: " + name).getTypes();
        for (int i = 0; i < values.length; i++) values[i] = Parser.parse(types[i], values[i]);
        return packetClass.getConstructor(types).newInstance(values);
    }

    public static Object getPacket(Class<?> packetClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        String name = packetClass.getSimpleName();
        Class<?>[] types = Objects.requireNonNull(Packets.get(name), "Invalid packet: " + name).getTypes();
        return packetClass.getConstructor(types).newInstance();
    }
}
