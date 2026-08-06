package de.sleepystaku.sleepyshammers.item;

import de.sleepystaku.sleepyshammers.SleepysHammers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SleepysHammers.MODID);

    public static final DeferredItem<Item> WOODEN_HAMMER = ITEMS.registerItem("wooden_hammer", properties ->
            new HammerItem(properties.pickaxe(ToolMaterial.WOOD, 3, -2.8f)));

    public static final DeferredItem<Item> STONE_HAMMER = ITEMS.registerItem("stone_hammer", properties ->
            new HammerItem(properties.pickaxe(ToolMaterial.STONE, 4, -2.8f)));

    public static final DeferredItem<Item> COPPER_HAMMER = ITEMS.registerItem("copper_hammer", properties ->
            new HammerItem(properties.pickaxe(ToolMaterial.COPPER, 4, -2.8f)));

    public static final DeferredItem<Item> IRON_HAMMER = ITEMS.registerItem("iron_hammer", properties ->
            new HammerItem(properties.pickaxe(ToolMaterial.IRON, 4, -2.8f)));

    public static final DeferredItem<Item> GOLDEN_HAMMER = ITEMS.registerItem("golden_hammer", properties ->
            new HammerItem(properties.pickaxe(ToolMaterial.GOLD, 3, -2.8f)));

    public static final DeferredItem<Item> DIAMOND_HAMMER = ITEMS.registerItem("diamond_hammer", properties ->
            new HammerItem(properties.pickaxe(ToolMaterial.DIAMOND, 4, -2.8f)));

    public static final DeferredItem<Item> NETHERITE_HAMMER = ITEMS.registerItem("netherite_hammer", properties ->
            new HammerItem(properties.pickaxe(ToolMaterial.NETHERITE, 4, -2.8f)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
