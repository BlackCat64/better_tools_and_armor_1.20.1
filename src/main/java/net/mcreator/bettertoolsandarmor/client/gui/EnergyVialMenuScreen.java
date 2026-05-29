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
import net.mcreator.bettertoolsandarmor.procedures.*;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModScreens;

import java.util.stream.Collectors;
import java.util.Arrays;

import com.mojang.blaze3d.systems.RenderSystem;

public class EnergyVialMenuScreen extends AbstractContainerScreen<EnergyVialMenuMenu> implements BetterToolsModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Checkbox helmet_active;
	private Checkbox chestplate_active;
	private Checkbox leggings_active;
	private Checkbox boots_active;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("better_tools:textures/screens/energy_vial_menu.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("better_tools:textures/screens/gui_arrow.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("better_tools:textures/screens/gui_arrow.png");

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

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 1 && elementState instanceof Boolean logicState) {
			if (name.equals("helmet_active")) {
				if (helmet_active.selected() != logicState)
					helmet_active.onPress();
			} else if (name.equals("chestplate_active")) {
				if (chestplate_active.selected() != logicState)
					chestplate_active.onPress();
			} else if (name.equals("leggings_active")) {
				if (leggings_active.selected() != logicState)
					leggings_active.onPress();
			} else if (name.equals("boots_active")) {
				if (boots_active.selected() != logicState)
					boots_active.onPress();
			}
		}
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		boolean customTooltipShown = false;
		if (EnergyVialShowFuelTooltipProcedure.execute(entity))
			if (mouseX > leftPos + 4 && mouseX < leftPos + 28 && mouseY > topPos + 45 && mouseY < topPos + 69) {
				guiGraphics.renderTooltip(font, Component.translatable("gui.better_tools.energy_vial_menu.tooltip_insert_fuel_here"), mouseX, mouseY);
				customTooltipShown = true;
			}
		if (mouseX > leftPos + 139 && mouseX < leftPos + 163 && mouseY > topPos + 14 && mouseY < topPos + 38) {
			String hoverText = HelmetCheckboxTooltipProcedure.execute(entity);
			if (hoverText != null) {
				guiGraphics.renderComponentTooltip(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 139 && mouseX < leftPos + 163 && mouseY > topPos + 35 && mouseY < topPos + 59) {
			String hoverText = ChestplateCheckboxTooltipProcedure.execute(entity);
			if (hoverText != null) {
				guiGraphics.renderComponentTooltip(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 139 && mouseX < leftPos + 163 && mouseY > topPos + 56 && mouseY < topPos + 80) {
			String hoverText = LeggingsCheckboxTooltipProcedure.execute(entity);
			if (hoverText != null) {
				guiGraphics.renderComponentTooltip(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (mouseX > leftPos + 139 && mouseX < leftPos + 163 && mouseY > topPos + 77 && mouseY < topPos + 101) {
			String hoverText = BootsCheckboxTooltipProcedure.execute(entity);
			if (hoverText != null) {
				guiGraphics.renderComponentTooltip(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
			customTooltipShown = true;
		}
		if (!customTooltipShown)
			this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(IMAGE_0, this.leftPos + 32, this.topPos + 50, 0, 0, 22, 15, 22, 15);
		guiGraphics.blit(IMAGE_1, this.leftPos + 86, this.topPos + 50, 0, 0, 22, 15, 22, 15);
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
		boolean helmet_activeSelected = HelmetActiveWhenGuiOpenedProcedure.execute(entity);
		helmet_active = Checkbox.builder(Component.translatable("gui.better_tools.energy_vial_menu.helmet_active"), this.font).pos(this.leftPos + 141, this.topPos + 16).onValueChange((checkbox, value) -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 1, "helmet_active", value, false);
		}).selected(helmet_activeSelected).build();
		if (helmet_activeSelected)
			menu.sendMenuStateUpdate(entity, 1, "helmet_active", true, false);
		this.addRenderableWidget(helmet_active);
		boolean chestplate_activeSelected = ChestplateActiveWhenGuiOpenedProcedure.execute(entity);
		chestplate_active = Checkbox.builder(Component.translatable("gui.better_tools.energy_vial_menu.chestplate_active"), this.font).pos(this.leftPos + 141, this.topPos + 37).onValueChange((checkbox, value) -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 1, "chestplate_active", value, false);
		}).selected(chestplate_activeSelected).build();
		if (chestplate_activeSelected)
			menu.sendMenuStateUpdate(entity, 1, "chestplate_active", true, false);
		this.addRenderableWidget(chestplate_active);
		boolean leggings_activeSelected = LeggingsActiveWhenGuiOpenedProcedure.execute(entity);
		leggings_active = Checkbox.builder(Component.translatable("gui.better_tools.energy_vial_menu.leggings_active"), this.font).pos(this.leftPos + 141, this.topPos + 58).onValueChange((checkbox, value) -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 1, "leggings_active", value, false);
		}).selected(leggings_activeSelected).build();
		if (leggings_activeSelected)
			menu.sendMenuStateUpdate(entity, 1, "leggings_active", true, false);
		this.addRenderableWidget(leggings_active);
		boolean boots_activeSelected = BootsActiveWhenGuiOpenedProcedure.execute(entity);
		boots_active = Checkbox.builder(Component.translatable("gui.better_tools.energy_vial_menu.boots_active"), this.font).pos(this.leftPos + 141, this.topPos + 79).onValueChange((checkbox, value) -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 1, "boots_active", value, false);
		}).selected(boots_activeSelected).build();
		if (boots_activeSelected)
			menu.sendMenuStateUpdate(entity, 1, "boots_active", true, false);
		this.addRenderableWidget(boots_active);
	}
}