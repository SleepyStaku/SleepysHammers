package de.sleepystaku.sleepyshammers.datagen;

import de.sleepystaku.sleepyshammers.SleepysHammers;
import de.sleepystaku.sleepyshammers.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        shaped(RecipeCategory.TOOLS, ModItems.WOODEN_HAMMER.get())
                .pattern("###")
                .pattern("#S#")
                .pattern(" S ")
                .define('#', ItemTags.WOODEN_TOOL_MATERIALS)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.OAK_PLANKS), has(ItemTags.WOODEN_TOOL_MATERIALS))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .group("Wooden Hammer")
                .save(output);

        shaped(RecipeCategory.TOOLS, ModItems.STONE_HAMMER.get())
                .pattern("###")
                .pattern("#S#")
                .pattern(" S ")
                .define('#', Tags.Items.STONES)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.STONE), has(ItemTags.STONE_CRAFTING_MATERIALS))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .group("Stone Hammer")
                .save(output);


        shaped(RecipeCategory.TOOLS, ModItems.COPPER_HAMMER.get())
                .pattern("###")
                .pattern("#S#")
                .pattern(" S ")
                .define('#', Items.COPPER_INGOT)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .group("Copper Hammer")
                .save(output);

        shaped(RecipeCategory.TOOLS, ModItems.IRON_HAMMER.get())
                .pattern("###")
                .pattern("#S#")
                .pattern(" S ")
                .define('#', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .group("Iron Hammer")
                .save(output);

        shaped(RecipeCategory.TOOLS, ModItems.GOLDEN_HAMMER.get())
                .pattern("###")
                .pattern("#S#")
                .pattern(" S ")
                .define('#', Items.GOLD_INGOT)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .group("Golden Hammer")
                .save(output);

        shaped(RecipeCategory.TOOLS, ModItems.DIAMOND_HAMMER.get())
                .pattern("###")
                .pattern("#S#")
                .pattern(" S ")
                .define('#', Items.DIAMOND)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .group("Diamond Hammer")
                .save(output);

        netheriteSmithing(ModItems.DIAMOND_HAMMER.get(), ModItems.NETHERITE_HAMMER.get());

    }

    protected void netheriteSmithing(Item baseItem, Item resultItem) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(baseItem),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.TOOLS,
                        resultItem
                )
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .unlocks(getHasName(baseItem), has(baseItem))
                .save(output, SleepysHammers.MODID + ":" + getItemName(resultItem) + "_smithing");
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public @NonNull String getName() {
            return "SleepysHammers Recipes";
        }
    }
}