
package net.mcreator.bettertoolsandarmor.item;

import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.SlotContext;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import net.mcreator.bettertoolsandarmor.procedures.EnergyVialOpenGuiProcedure;
import net.mcreator.bettertoolsandarmor.procedures.EffectEnergyApplyCostProcedure;

public class EnergyVialItem extends Item implements ICurioItem {
	public EnergyVialItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		ItemStack itemstack = ar.getObject();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		EnergyVialOpenGuiProcedure.execute(world, x, y, z, entity);
		return ar;
	}

	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		EffectEnergyApplyCostProcedure.execute(slotContext.entity(), stack);
	}
}
