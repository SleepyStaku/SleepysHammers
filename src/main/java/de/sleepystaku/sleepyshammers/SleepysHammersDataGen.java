package de.sleepystaku.sleepyshammers;

import de.sleepystaku.sleepyshammers.datagen.ModModelProvider;
import de.sleepystaku.sleepyshammers.datagen.ModRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;


@EventBusSubscriber(modid = SleepysHammers.MODID)
public class SleepysHammersDataGen {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new ModModelProvider(packOutput));
        generator.addProvider(true, new ModRecipeProvider.Runner(packOutput, lookupProvider));
    }
}
