package net.mcreator.bettertoolsandarmor.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.bettertoolsandarmor.world.inventory.EnergyVialMenuMenu;
import net.mcreator.bettertoolsandarmor.procedures.LeggingsActiveWhenGuiOpenedProcedure;
import net.mcreator.bettertoolsandarmor.procedures.HelmetActiveWhenGuiOpenedProcedure;
import net.mcreator.bettertoolsandarmor.procedures.EnergyVialShowFuelTooltipProcedure;
import net.mcreator.bettertoolsandarmor.procedures.ChestplateActiveWhenGuiOpenedProcedure;
import net.mcreator.bettertoolsandarmor.procedures.BootsActiveWhenGuiOpenedProcedure;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class EnergyVialMenuScreen extends AbstractContainerScreen<EnergyVialMenuMenu> {
	private final static HashMap<String, Object> guistate = EnergyVialMenuMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Checkbox helmet_active;
	Checkbox chestplate_active;
	Checkbox leggings_active;
	Checkbox boots_active;

	public EnergyVialMenuScreen(EnergyVialMenuMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 192;
	}

	private static final ResourceLocation texture = new ResourceLocation("better_tools:textures/screens/energy_vial_menu.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (EnergyVialShowFuelTooltipProcedure.execute(entity))
			if (mouseX > leftPos + 4 && mouseX < leftPos + 28 && mouseY > topPos + 45 && mouseY < topPos + 69)
				guiGraphics.renderTooltip(font, Component.translatable("gui.better_tools.energy_vial_menu.tooltip_insert_fuel_here"), mouseX, mouseY);
		if (mouseX > leftPos + 139 && mouseX < leftPos + 163 && mouseY > topPos + 14 && mouseY < topPos + 38)
			guiGraphics.renderTooltip(font, Component.translatable("gui.better_tools.energy_vial_menu.tooltip_activate_helmet_effect"), mouseX, mouseY);
		if (mouseX > leftPos + 139 && mouseX < leftPos + 163 && mouseY > topPos + 35 && mouseY < topPos + 59)
			guiGraphics.renderTooltip(font, Component.translatable("gui.better_tools.energy_vial_menu.tooltip_activate_chestplate_effect"), mouseX, mouseY);
		if (mouseX > leftPos + 139 && mouseX < leftPos + 163 && mouseY > topPos + 56 && mouseY < topPos + 80)
			guiGraphics.renderTooltip(font, Component.translatable("gui.better_tools.energy_vial_menu.tooltip_activate_leggings_effect"), mouseX, mouseY);
		if (mouseX > leftPos + 139 && mouseX < leftPos + 163 && mouseY > topPos + 77 && mouseY < topPos + 101)
			guiGraphics.renderTooltip(font, Component.translatable("gui.better_tools.energy_vial_menu.tooltip_activate_boots_effect"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("better_tools:textures/screens/gui_arrow.png"), this.leftPos + 32, this.topPos + 50, 0, 0, 22, 15, 22, 15);

		guiGraphics.blit(new ResourceLocation("better_tools:textures/screens/gui_arrow.png"), this.leftPos + 86, this.topPos + 50, 0, 0, 22, 15, 22, 15);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.better_tools.energy_vial_menu.label_energy_vial"), 60, 5, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		helmet_active = new Checkbox(this.leftPos + 141, this.topPos + 16, 20, 20, Component.translatable("gui.better_tools.energy_vial_menu.helmet_active"),

				HelmetActiveWhenGuiOpenedProcedure.execute(world, entity));
		guistate.put("checkbox:helmet_active", helmet_active);
		this.addRenderableWidget(helmet_active);
		chestplate_active = new Checkbox(this.leftPos + 141, this.topPos + 37, 20, 20, Component.translatable("gui.better_tools.energy_vial_menu.chestplate_active"),

				ChestplateActiveWhenGuiOpenedProcedure.execute(world, entity));
		guistate.put("checkbox:chestplate_active", chestplate_active);
		this.addRenderableWidget(chestplate_active);
		leggings_active = new Checkbox(this.leftPos + 141, this.topPos + 58, 20, 20, Component.translatable("gui.better_tools.energy_vial_menu.leggings_active"),

				LeggingsActiveWhenGuiOpenedProcedure.execute(world, entity));
		guistate.put("checkbox:leggings_active", leggings_active);
		this.addRenderableWidget(leggings_active);
		boots_active = new Checkbox(this.leftPos + 141, this.topPos + 79, 20, 20, Component.translatable("gui.better_tools.energy_vial_menu.boots_active"),

				BootsActiveWhenGuiOpenedProcedure.execute(world, entity));
		guistate.put("checkbox:boots_active", boots_active);
		this.addRenderableWidget(boots_active);
	}
}
