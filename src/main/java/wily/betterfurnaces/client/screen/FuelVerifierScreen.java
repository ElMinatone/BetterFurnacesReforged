package wily.betterfurnaces.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import wily.betterfurnaces.BetterFurnacesReforged;
import wily.betterfurnaces.inventory.FuelVerifierMenu;
import wily.factoryapi.base.client.FactoryGuiGraphics;


public class FuelVerifierScreen extends AbstractBasicScreen<FuelVerifierMenu> {

    public static final Identifier GUI = BetterFurnacesReforged.createModLocation("textures/container/fuel_verifier_gui.png");
    Inventory playerInv;
    Component name;

    public FuelVerifierScreen(FuelVerifierMenu t, Inventory inv, Component name) {
        super(t, inv, name);
        playerInv = inv;
        this.name = name;
    }



    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        Component invname = this.playerInv.getDisplayName();
        Component burntime = Component.translatable("gui.betterfurnacesreforged.fuel.melts").append(Component.literal(String.valueOf(( this.getMenu()).getBurnTime()))).append( Component.translatable("gui.betterfurnacesreforged.fuel.items"));
        graphics.text(font, invname, 7, this.imageHeight - 93, 4210752,false);
        if (this.getMenu().getBurnTime() > 0)
            graphics.text(font, burntime, this.imageWidth / 2 - font.width(burntime.getString()) / 2, 6, 4210752,false);
        else graphics.text(font, name, 7 + this.imageWidth / 2 - font.width(name.getString()) / 2, 6, 4210752,false);
    }


    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTicks) {
        wily.factoryapi.util.FactoryScreenUtil.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        FactoryGuiGraphics.of(graphics).blit(GUI, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);
        super.extractContents(graphics, mouseX, mouseY, partialTicks);
        int i;
        i = (this.getMenu()).getBurnTimeScaled(26);
        if (i > 0) {
            FactoryGuiGraphics.of(graphics).blit(GUI, leftPos + 74, topPos + 13 + 26 - i, 176, 24 - i, 28, i + 2);
        }
    }


}
