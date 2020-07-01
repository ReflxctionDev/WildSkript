package net.dzikoysk.wildskript.objects.hologram;

import net.dzikoysk.wildskript.WildSkript;
import net.dzikoysk.wildskript.util.PacketUtils;
import net.dzikoysk.wildskript.util.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Hologram {

    public static List<Hologram> holograms = new ArrayList<>();
    private static final double distance = 0.23;

    private String id;
    private List<String> lines;
    private List<Integer> ids;
    private boolean showing;
    private Location location;

    public Hologram(String id) {
        this.id = id;
        this.lines = new ArrayList<>();
        this.ids = new ArrayList<>();
        holograms.add(this);
    }

    public static Hologram get(String id) {
        for (Hologram h : holograms) if (h.getID().equals(id)) return h;
        return new Hologram(id);
    }

    public void change(String[] lines) {
        this.lines.clear();
        if (showing) {
            try {
                destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Collections.addAll(this.lines, lines);
            if (this.location != null) show(this.location);
            return;
        }
        Collections.addAll(this.lines, lines);
    }

    public void show(Location loc) {
        if (showing) return;
        if (this.lines == null) return;
        Location first = loc.clone().add(0, ((float) (this.lines.size() / 2)) * distance, 0);
        for (String line : this.lines) {
            ids.addAll(Objects.requireNonNull(showLine(first.clone(), line)));
            first.subtract(0, distance, 0);
        }
        showing = true;
        this.location = loc;
    }

    public void show(Location loc, int sec) {
        show(loc);
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    destroy();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskLater(WildSkript.getInstance(), sec * 20);
    }

    public void destroy() throws Exception {
        if (!showing) return;
        int[] ints = new int[ids.size()];
        for (int j = 0; j < ints.length; j++) if (j != 0) ints[j] = ids.get(j);
        Class<?> packetDestroy = ReflectionUtils.getCraftClass("PacketPlayOutEntityDestroy");
        Object packet = packetDestroy.getConstructor(new Class<?>[]{int[].class}).newInstance(ints);
        for (Player p : Bukkit.getOnlinePlayers()) PacketUtils.sendPacket(p, packet);
        showing = false;
        this.location = null;
        this.ids.clear();
    }

    private static List<Integer> showLine(Location loc, String text) {
        Class<?> Entity = ReflectionUtils.getCraftClass("Entity");
        Class<?> EntityLiving = ReflectionUtils.getCraftClass("EntityLiving");
        Class<?> EntityWitherSkull = ReflectionUtils.getCraftClass("EntityWitherSkull");
        Class<?> EntityHorse = ReflectionUtils.getCraftClass("EntityHorse");
        Class<?> packetOnlyClass = ReflectionUtils.getCraftClass("PacketPlayOutSpawnEntity");
        Class<?> packetLivingClass = ReflectionUtils.getCraftClass("PacketPlayOutSpawnEntityLiving");
        Class<?> packetAttachClass = ReflectionUtils.getCraftClass("PacketPlayOutAttachEntity");
        try {
            Object world = ReflectionUtils.getHandle(loc.getWorld());
            Object skull = EntityWitherSkull.getConstructor(ReflectionUtils.getCraftClass("World")).newInstance(world);
            Objects.requireNonNull(ReflectionUtils.getMethod(EntityWitherSkull, "setLocation", double.class, double.class, double.class, float.class, float.class)).invoke(skull, loc.getX(), loc.getY() + 1 + 55, loc.getZ(), 0, 0);
            Object skull_packet = packetOnlyClass.getConstructor(new Class<?>[]{Entity, int.class}).newInstance(skull, 64);

            Object horse = EntityHorse.getConstructor(ReflectionUtils.getCraftClass("World")).newInstance(world);
            Objects.requireNonNull(ReflectionUtils.getMethod(EntityHorse, "setLocation", double.class, double.class, double.class, float.class, float.class)).invoke(horse, loc.getX(), loc.getY() + 55, loc.getZ(), 0, 0);
            Objects.requireNonNull(ReflectionUtils.getMethod(EntityHorse, "setAge", int.class)).invoke(horse, -1700000);
            Objects.requireNonNull(ReflectionUtils.getMethod(EntityHorse, "setCustomName", String.class)).invoke(horse, text);
            Objects.requireNonNull(ReflectionUtils.getMethod(EntityHorse, "setCustomNameVisible", boolean.class)).invoke(horse, true);
            Object packedt = packetLivingClass.getConstructor(new Class<?>[]{EntityLiving}).newInstance(horse);
            for (Player player : Objects.requireNonNull(loc.getWorld()).getPlayers()) {
                PacketUtils.sendPacket(player, packedt);
                PacketUtils.sendPacket(player, skull_packet);
                Object pa = packetAttachClass.getConstructor(new Class<?>[]{int.class, Entity, Entity}).newInstance(0, horse, skull);
                PacketUtils.sendPacket(player, pa);
            }
            int sid = (int) Objects.requireNonNull(ReflectionUtils.getMethod(EntityWitherSkull, "getId")).invoke(skull);
            int hid = (int) Objects.requireNonNull(ReflectionUtils.getMethod(EntityHorse, "getId")).invoke(horse);
            return Arrays.asList(sid, hid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete() {
        holograms.remove(this);
        try {
            destroy();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getID() {
        return this.id;
    }
}