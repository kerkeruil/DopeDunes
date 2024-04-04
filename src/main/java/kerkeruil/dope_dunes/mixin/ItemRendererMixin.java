package kerkeruil.dope_dunes.mixin;

import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.registry.ModItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useBlinkOrbModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (stack.isOf(ModItems.BLINK_ORB) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).getModels().getModelManager().getModel(new ModelIdentifier(DopeDunes.MOD_ID, "blink_orb_3d", "inventory"));
        }
        return value;
    }
}