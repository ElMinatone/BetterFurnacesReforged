package wily.betterfurnaces.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.phys.Vec3;
import wily.betterfurnaces.blockentity.ForgeBlockEntity;

public class ForgeRenderer implements BlockEntityRenderer<ForgeBlockEntity, BlockEntityRenderState> {
    public ForgeRenderer(BlockEntityRendererProvider.Context context) {
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
    public boolean shouldRender(ForgeBlockEntity be, Vec3 vec) {
        return be.getBlockPos().getCenter().multiply(1.0, 0.0, 1.0).closerThan(vec.multiply(1.0, 0.0, 1.0), this.getViewDistance());
    }
}
