package ladysnake.beem;

import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class BeeNestBlockEntityRenderer extends BlockEntityRenderer<BeehiveBlockEntity> {
    public static final Identifier BEAM_TEXTURE = new Identifier("beem:textures/entity/beem.png");
    public static final DyeColor BEAM_COLOR = DyeColor.YELLOW;

    public BeeNestBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(BeehiveBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (blockEntity.getWorld().getBlockState(blockEntity.getPos()).getBlock().equals(Blocks.BEE_NEST) && Beem.beem) {
            long time = blockEntity.getWorld().getTime();
            BeaconBlockEntityRenderer.renderLightBeam(matrices, vertexConsumers, BEAM_TEXTURE, tickDelta, 1.0f, time, 0, 256, BEAM_COLOR.getColorComponents(), 0.25F, 0.35F);
            BeaconBlockEntityRenderer.renderLightBeam(matrices, vertexConsumers, BEAM_TEXTURE, tickDelta, 1.0f, time, 0, -256, BEAM_COLOR.getColorComponents(), 0.25F, 0.35F);
        }
    }
}
