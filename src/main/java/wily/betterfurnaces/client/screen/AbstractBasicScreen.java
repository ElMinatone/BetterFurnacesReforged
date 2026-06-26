package wily.betterfurnaces.client.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import wily.factoryapi.base.client.IWindowWidget;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBasicScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> implements IWindowWidget {

    protected final List<Renderable> renderables = new ArrayList<>();
    public AbstractBasicScreen(T p_97741_, Inventory p_97742_, Component p_97743_) {
        this(p_97741_, p_97742_, p_97743_, 176, 166);
    }

    public AbstractBasicScreen(T p_97741_, Inventory p_97742_, Component p_97743_, int imageWidth, int imageHeight) {
        super(p_97741_, p_97742_, p_97743_, imageWidth, imageHeight);
    }

    @Override
    protected void init() {
        renderables.clear();
        super.init();
    }

    @Override
    public Rect2i getBounds() {
        return new Rect2i(leftPos,topPos,imageWidth,imageHeight);
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int i, int j, float f) {
        //? if <1.20.2 {
        /*renderBackground(graphics);
        *///?}
        super.extractRenderState(graphics, i, j, f);
        IWindowWidget.super.extractRenderState(graphics,i,j,f);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean bl) {
        if (IWindowWidget.super.mouseClicked(event, bl)) return true;
        return super.mouseClicked(event, bl);
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        this.setDragging(false);
        if (IWindowWidget.super.mouseReleased(event)) return true;
        return super.mouseReleased(event);
    }
    @Override
    public boolean mouseDragged(MouseButtonEvent event, double f, double g) {
        if (IWindowWidget.super.mouseDragged(event, f, g)) return true;
        return super.mouseDragged(event, f, g);
    }

    @Override
    public <R extends Renderable> R addNestedRenderable(R drawable) {
        renderables.add(drawable);
        return drawable;
    }

    @Override
    public List<? extends Renderable> getNestedRenderables() {
        return renderables;
    }
}
