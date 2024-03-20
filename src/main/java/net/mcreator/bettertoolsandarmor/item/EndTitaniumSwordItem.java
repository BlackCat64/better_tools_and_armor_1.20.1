
package net.mcreator.bettertoolsandarmor.item;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

import net.mcreator.bettertoolsandarmor.procedures.EndTitaniumToolsProcedureProcedure;
import net.mcreator.bettertoolsandarmor.procedures.EndTitaniumSwordGlowProcedure;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.List;

public class EndTitaniumSwordItem extends SwordItem {
	public EndTitaniumSwordItem() {
		super(new Tier() {
			public int getUses() {
				return 2700;
			}

			public float getSpeed() {
				return 12f;
			}

			public float getAttackDamageBonus() {
				return 6f;
			}

			public int getLevel() {
				return 5;
			}

			public int getEnchantmentValue() {
				return 18;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(BetterToolsModItems.END_TITANIUM_INGOT.get()));
			}
		}, 3, -2.4f, new Item.Properties().fireResistant());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("\u00A75Increased attack damage in The End"));
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		EndTitaniumToolsProcedureProcedure.execute(entity);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean isFoil(ItemStack itemstack) {
		Entity entity = Minecraft.getInstance().player;
		return EndTitaniumSwordGlowProcedure.execute(entity);
	}
}
