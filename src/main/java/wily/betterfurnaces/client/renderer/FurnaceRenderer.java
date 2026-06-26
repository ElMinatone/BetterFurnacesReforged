package wily.betterfurnaces.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import wily.betterfurnaces.BetterFurnacesReforged;
import wily.betterfurnaces.blockentity.SmeltingBlockEntity;

public class FurnaceRenderer implements BlockEntityRenderer<SmeltingBlockEntity, BlockEntityRenderState> {
    public FurnaceRenderer(BlockEntityRendererProvider.Context context) {
    }

    public enum FurnaceOverlay {
        FURNACE("furnace"), BLAST("blast"), SMOKER("smoker"), GENERATOR("generator");

        public final Identifier id;
        public final Identifier activeId;

        FurnaceOverlay(String id) {
            this.id = BetterFurnacesReforged.createModLocation(id + "_overlay");
            this.activeId = BetterFurnacesReforged.createModLocation(id + "_on_overlay");
        }

        public Identifier getId(boolean active) {
            return active ? activeId : id;
        }
    }

    public static Identifier getFront(int type, boolean active) {
        return FurnaceOverlay.values()[type].getId(active);
    }

    @Override
    public BlockEntityRenderState createRenderState() {
        return new BlockEntityRenderState();
    }

    @Override
    public void submit(BlockEntityRenderState state, PoseStack stack, SubmitNodeCollector collector, CameraRenderState cameraState) {
    }

    @Override
    public int getViewDistance() {
        return 256;
    }

    @Override
    public boolean shouldRender(SmeltingBlockEntity be, Vec3 vec) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(1.0, 0.0, 1.0).closerThan(vec.multiply(1.0, 0.0, 1.0), this.getViewDistance());
    }
}
