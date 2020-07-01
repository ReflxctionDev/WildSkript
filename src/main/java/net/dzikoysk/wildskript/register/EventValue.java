package net.dzikoysk.wildskript.register;

import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.dzikoysk.wildskript.events.skript.EvtJump;
import net.dzikoysk.wildskript.util.doc.Element;
import net.dzikoysk.wildskript.util.doc.Type;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Player;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.HorseJumpEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventValue {

    public static void registerEventValues() {

        List<Element> es = new ArrayList<>();

        es.add(new Element(Type.EVENT_VALUE).name("[On Enchant] event-item"));
        EventValues.registerEventValue(EnchantItemEvent.class, ItemStack.class, new Getter<ItemStack, EnchantItemEvent>() {
            public ItemStack get(EnchantItemEvent event) {
                return event.getItem();
            }
        }, 0, null, (Class<? extends EnchantItemEvent>[]) null);

        es.add(new Element(Type.EVENT_VALUE).name("[On Enchant] player"));
        EventValues.registerEventValue(EnchantItemEvent.class, Player.class, new Getter<Player, EnchantItemEvent>() {
            public Player get(EnchantItemEvent event) {
                return event.getEnchanter();
            }
        }, 0, null, (Class<? extends EnchantItemEvent>[]) null);

        es.add(new Element(Type.EVENT_VALUE).name("[On Inventory Click] event-item"));
        EventValues.registerEventValue(InventoryClickEvent.class, ItemStack.class, new Getter<ItemStack, InventoryClickEvent>() {
            public ItemStack get(InventoryClickEvent event) {
                return event.getCurrentItem();
            }
        }, 0, null, (Class<? extends InventoryClickEvent>[]) null);

        es.add(new Element(Type.EVENT_VALUE).name("[On Inventory Click] player"));
        EventValues.registerEventValue(InventoryClickEvent.class, Player.class, new Getter<Player, InventoryClickEvent>() {
            public Player get(InventoryClickEvent event) {
                return (Player) event.getWhoClicked();
            }
        }, 0, null, (Class<? extends InventoryClickEvent>[]) null);

        es.add(new Element(Type.EVENT_VALUE).name("[On Close Inventory] player"));
        EventValues.registerEventValue(InventoryCloseEvent.class, Player.class, new Getter<Player, InventoryCloseEvent>() {
            public Player get(InventoryCloseEvent event) {
                return (Player) event.getPlayer();
            }
        }, 0, null, (Class<? extends InventoryCloseEvent>[]) null);

        es.add(new Element(Type.EVENT_VALUE).name("[Server Ping] IP"));
        EventValues.registerEventValue(ServerListPingEvent.class, String.class, new Getter<String, ServerListPingEvent>() {
            public String get(ServerListPingEvent event) {
                return event.getAddress().toString();
            }
        }, 0, null, (Class<? extends ServerListPingEvent>[]) null);

        es.add(new Element(Type.EVENT_VALUE).name("[On Jump] player"));
        EventValues.registerEventValue(EvtJump.class, Player.class, new Getter<Player, EvtJump>() {
            public Player get(EvtJump event) {
                return event.getPlayer();
            }
        }, 0, null, (Class<? extends EvtJump>[]) null);

        es.add(new Element(Type.EVENT_VALUE).name("[On Prepare Item Craft Event] player"));
        EventValues.registerEventValue(PrepareItemCraftEvent.class, Player.class, new Getter<Player, PrepareItemCraftEvent>() {
            public Player get(PrepareItemCraftEvent event) {
                return (Player) event.getView().getPlayer();
            }
        }, 0, null, (Class<? extends PrepareItemCraftEvent>[]) null);

        es.add(new Element(Type.EVENT_VALUE).name("[On Prepare Item Craft Event] event-item"));
        EventValues.registerEventValue(PrepareItemCraftEvent.class, ItemStack.class, new Getter<ItemStack, PrepareItemCraftEvent>() {
            public ItemStack get(PrepareItemCraftEvent event) {
                return Objects.requireNonNull(event.getRecipe()).getResult();
            }
        }, 0, null, (Class<? extends PrepareItemCraftEvent>[]) null);

        es.add(new Element(Type.EVENT_VALUE).name("[On Horse Jump] horse"));
        EventValues.registerEventValue(HorseJumpEvent.class, AbstractHorse.class, new Getter<AbstractHorse, HorseJumpEvent>() {
            public AbstractHorse get(HorseJumpEvent event) {
                return event.getEntity();
            }
        }, 0, null, (Class<? extends HorseJumpEvent>[]) null);

        es.add(new Element(Type.EVENT_VALUE).name("[On Book Edit] player"));
        EventValues.registerEventValue(PlayerEditBookEvent.class, Player.class, new Getter<Player, PlayerEditBookEvent>() {
            public Player get(PlayerEditBookEvent event) {
                return event.getPlayer();
            }
        }, 0, null, (Class<? extends PlayerEditBookEvent>[]) null);

        es.add(new Element(Type.EVENT_VALUE).name("[On Craft Item] event-item"));
        EventValues.registerEventValue(CraftItemEvent.class, ItemStack.class, new Getter<ItemStack, CraftItemEvent>() {
            public ItemStack get(CraftItemEvent event) {
                return event.getRecipe().getResult();
            }
        }, 0, null, (Class<? extends CraftItemEvent>[]) null);

        RegisterManager.registerEventValues(es);

    }

}
