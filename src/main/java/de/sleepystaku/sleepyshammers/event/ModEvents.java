package de.sleepystaku.sleepyshammers.event;

import de.sleepystaku.sleepyshammers.SleepysHammers;
import de.sleepystaku.sleepyshammers.item.HammerItem;
import de.sleepystaku.sleepyshammers.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.level.block.BreakBlockEvent;

import java.util.HashSet;
import java.util.Set;

@EventBusSubscriber(modid = SleepysHammers.MODID)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();


    // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    @SubscribeEvent
    public static void onHammerUsage(BreakBlockEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if (mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            int blockBreakRange = hammer.getRange();
            if (HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for (BlockPos pos : HammerItem.getBlocksToBeDestroyed(blockBreakRange, initialBlockPos, serverPlayer)) {
                if (pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

    @SubscribeEvent
    public static void onBuildCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            CreativeModeTab.TabVisibility visibility = CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;

            event.insertAfter(Items.WOODEN_PICKAXE.getDefaultInstance(), ModItems.WOODEN_HAMMER.toStack(), visibility);
            event.insertAfter(Items.STONE_PICKAXE.getDefaultInstance(), ModItems.STONE_HAMMER.toStack(), visibility);
            event.insertAfter(Items.COPPER_PICKAXE.getDefaultInstance(), ModItems.COPPER_HAMMER.toStack(), visibility);
            event.insertAfter(Items.IRON_PICKAXE.getDefaultInstance(), ModItems.IRON_HAMMER.toStack(), visibility);
            event.insertAfter(Items.GOLDEN_PICKAXE.getDefaultInstance(), ModItems.GOLDEN_HAMMER.toStack(), visibility);
            event.insertAfter(Items.DIAMOND_PICKAXE.getDefaultInstance(), ModItems.DIAMOND_HAMMER.toStack(), visibility);
            event.insertAfter(Items.NETHERITE_PICKAXE.getDefaultInstance(), ModItems.NETHERITE_HAMMER.toStack(), visibility);
        }
    }
}