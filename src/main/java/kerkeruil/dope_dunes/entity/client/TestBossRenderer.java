package kerkeruil.dope_dunes.entity.client;

import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.entity.custom.TestBossEntity;
import kerkeruil.dope_dunes.entity.layer.ModModelLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TestBossRenderer extends MobEntityRenderer<TestBossEntity, TestBossModel<TestBossEntity>> {
    private static final Identifier TEXTURE = new Identifier(DopeDunes.MOD_ID, "textures/entity/test_boss.png");
    private static float sizeMultiplier = 1.5f;
    public TestBossRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new TestBossModel<>(ctx.getPart(ModModelLayers.TEST_BOSS)), 0.6f);
    }

    @Override
    public Identifier getTexture(TestBossEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(TestBossEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(sizeMultiplier, sizeMultiplier, sizeMultiplier);

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}