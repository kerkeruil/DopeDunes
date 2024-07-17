package kerkeruil.dope_dunes.entity.client;

import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.entity.custom.TestBossEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class TestBossModel extends GeoModel<TestBossEntity> {
    @Override
    public Identifier getModelResource(TestBossEntity animatable) {
        return new Identifier(DopeDunes.MOD_ID, "geo/test_boss.geo.json");
    }

    @Override
    public Identifier getTextureResource(TestBossEntity animatable) {
        return new Identifier(DopeDunes.MOD_ID, "textures/entity/test_boss.png");
    }

    @Override
    public Identifier getAnimationResource(TestBossEntity animatable) {
        return new Identifier(DopeDunes.MOD_ID, "animations/test_boss.animation.json");
    }

    @Override
    public void setCustomAnimations(TestBossEntity animatable, long instanceId, AnimationState<TestBossEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}