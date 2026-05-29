package net.mcreator.bettertoolsandarmor.recipe.brewing;

import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.common.brewing.IBrewingRecipe;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

@EventBusSubscriber
public class DescensionPotionRecipeBrewingRecipe implements IBrewingRecipe {
	@SubscribeEvent
	public static void init(RegisterBrewingRecipesEvent event) {
		event.getBuilder().addRecipe(new DescensionPotionRecipeBrewingRecipe());
	}

	@Override
	public boolean isInput(ItemStack input) {
		return Ingredient.of(new ItemStack(BetterToolsModItems.ASCENSION_POTION.get())).test(input);
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return Ingredient.of(new ItemStack(Items.FERMENTED_SPIDER_EYE)).test(ingredient);
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		if (isInput(input) && isIngredient(ingredient)) {
			return new ItemStack(BetterToolsModItems.DESCENSION_POTION.get());
		}
		return ItemStack.EMPTY;
	}
}