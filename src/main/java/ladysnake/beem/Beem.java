package ladysnake.beem;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class Beem implements ClientModInitializer {
    private static final FabricKeyBinding TOGGLE_BEEM = FabricKeyBinding.Builder.create(
            new Identifier("beem:toggle_beem"),
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            "key.categories.misc"
    ).build();
    static boolean beem = true;

    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.INSTANCE.register(BlockEntityType.BEEHIVE, BeeNestBlockEntityRenderer::new);
        KeyBindingRegistry.INSTANCE.register(TOGGLE_BEEM);
        ClientTickCallback.EVENT.register(e -> {
            while (TOGGLE_BEEM.wasPressed()) {
                beem = !beem;
                if (MinecraftClient.getInstance().player != null) {
                    MinecraftClient.getInstance().player.addChatMessage(new TranslatableText("message.beem.toggled."+beem).formatted(Formatting.GOLD), false);
                }
            }
        });
    }
}
