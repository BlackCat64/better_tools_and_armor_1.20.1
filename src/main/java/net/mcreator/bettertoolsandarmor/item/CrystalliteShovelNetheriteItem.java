
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.procedures.CrystalliteNetheriteShovelRemoveWitherProcedure;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.List;

public class CrystalliteShovelNetheriteItem extends ShovelItem {
	public CrystalliteShovelNetheriteItem() {
		super(new Tier() {
			public int getUses() {
				return 2250;
			}

			public float getSpeed() {
				return 11f;
			}

			public float getAttackDamageBonus() {
				return 7.5f;
			}

			public int getLevel() {
				return 5;
			}

			public int getEnchantmentValue() {
				return 20;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(Items.NETHERITE_INGOT));
			}
		}, 1, -3f, new Item.Properties().fireResistant());
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		CrystalliteNetheriteShovelRemoveWitherProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, ar.getObject());
		return ar;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("\u00A77Upgrade: \u00A78Netherite"));
		list.add(Component.literal("\u00A77Abilities:"));
		list.add(Component.literal("\u00A78Nether Infused - Right-click to remove any Wither effects"));
		list.add(Component.literal("\u00A78Mines all Crimson and Warped blocks instantly"));
	}
}
