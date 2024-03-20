
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.InteractionResult;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.procedures.CrystalliteHoeEmeraldProcedureProcedure;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.List;

public class CrystalliteHoeEmeraldItem extends HoeItem {
	public CrystalliteHoeEmeraldItem() {
		super(new Tier() {
			public int getUses() {
				return 1800;
			}

			public float getSpeed() {
				return 10f;
			}

			public float getAttackDamageBonus() {
				return 0.5f;
			}

			public int getLevel() {
				return 5;
			}

			public int getEnchantmentValue() {
				return 20;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(Items.EMERALD));
			}
		}, 0, 0f, new Item.Properties().fireResistant());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("\u00A77Upgrade: \u00A7aEmerald"));
		list.add(Component.literal("\u00A77Abilities:"));
		list.add(Component.literal("\u00A7aNourishing - Right-click to grow surrounding crops"));
		list.add(Component.literal("\u00A7aRight-click while crouching to turn dirt into grass"));
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		CrystalliteHoeEmeraldProcedureProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), context.getLevel().getBlockState(context.getClickedPos()),
				context.getPlayer(), context.getItemInHand());
		return InteractionResult.SUCCESS;
	}
}
