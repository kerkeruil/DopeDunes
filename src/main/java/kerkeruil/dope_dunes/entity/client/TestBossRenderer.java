package kerkeruil.dope_dunes.entity.client;

import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.entity.custom.TestBossEntity;
import kerkeruil.dope_dunes.entity.layer.ModModelLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TestBossRenderer extends GeoEntityRenderer<TestBossEntity> {
    public TestBossRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new TestBossModel());
    }

    @Override
    public Identifier getTextureLocation(TestBossEntity animatable) {
        return new Identifier(DopeDunes.MOD_ID, "textures/entity/test_boss.png");
    }

    @Override
    public void render(TestBossEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        //TODO useless but maybe a good example?
        if(entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}