//package kerkeruil.dope_dunes.entity.client;
//
//import kerkeruil.dope_dunes.entity.animations.ModAnimations;
//import kerkeruil.dope_dunes.entity.custom.TestBossEntity;
//import net.minecraft.client.model.*;
//import net.minecraft.client.render.VertexConsumer;
//import net.minecraft.client.render.entity.model.SinglePartEntityModel;
//import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.util.math.MathHelper;
//
//public class TestBossModelOld<T extends TestBossEntity> extends SinglePartEntityModel<T> {
//    private final ModelPart test_boss;
//    private final ModelPart head;
//
//    public TestBossModelOld(ModelPart root) {
//        this.test_boss = root.getChild("test_boss");
//        this.head = test_boss.getChild("body").getChild("upper_body").getChild("head");
//    }
//    public static TexturedModelData getTexturedModelData() {
//        ModelData modelData = new ModelData();
//        ModelPartData modelPartData = modelData.getRoot();
//        ModelPartData test_boss = modelPartData.addChild("test_boss", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
//
//        ModelPartData body = test_boss.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//
//        ModelPartData lower_body = body.addChild("lower_body", ModelPartBuilder.create().uv(16, 22).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -18.0F, 0.0F));
//
//        ModelPartData left_leg = lower_body.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(2.0F, 6.0F, 0.0F));
//
//        ModelPartData left_leg1 = left_leg.addChild("left_leg1", ModelPartBuilder.create().uv(0, 22).cuboid(-1.0F, 0.0F, -1.1F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.1F));
//
//        ModelPartData left_leg2 = left_leg1.addChild("left_leg2", ModelPartBuilder.create().uv(0, 22).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.0F, -0.1F));
//
//        ModelPartData right_leg = lower_body.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 6.0F, 0.0F));
//
//        ModelPartData right_leg1 = right_leg.addChild("right_leg1", ModelPartBuilder.create().uv(0, 16).cuboid(-1.0F, 0.0F, -1.1F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.1F));
//
//        ModelPartData right_leg2 = right_leg1.addChild("right_leg2", ModelPartBuilder.create().uv(0, 16).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.0F, -0.1F));
//
//        ModelPartData upper_body = body.addChild("upper_body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, -7.0F, -2.0F, 8.0F, 7.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -17.0F, 0.0F));
//
//        ModelPartData head = upper_body.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -7.0F, 0.0F));
//
//        ModelPartData head1 = head.addChild("head1", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//
//        ModelPartData head2 = head.addChild("head2", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//
//        ModelPartData headwear2 = head.addChild("headwear2", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//
//        ModelPartData headwear = head.addChild("headwear", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//
//        ModelPartData left_arm = upper_body.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, -6.0F, 0.0F));
//
//        ModelPartData left_arm1 = left_arm.addChild("left_arm1", ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(1.0F, 1.0F, 0.0F));
//
//        ModelPartData left_arm2 = left_arm1.addChild("left_arm2", ModelPartBuilder.create().uv(40, 22).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 4.0F, 0.0F));
//
//        ModelPartData right_arm = upper_body.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, -6.0F, 0.0F));
//
//        ModelPartData right_arm1 = right_arm.addChild("right_arm1", ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-2.0F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 1.0F, 0.0F));
//
//        ModelPartData right_arm2 = right_arm1.addChild("right_arm2", ModelPartBuilder.create().uv(40, 22).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-1.0F, 4.0F, 0.0F));
//        return TexturedModelData.of(modelData, 64, 32);
//    }
//
//    private void setHeadAngles(TestBossEntity entity, float headYaw, float headPitch, float animationProgress) {
//        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
//        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);
//
//        this.head.yaw = headYaw * 0.017453292F;
//        this.head.pitch = headPitch * 0.017453292F;
//    }
//
//    @Override
//    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
//        test_boss.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
//    }
//
//    @Override
//    public ModelPart getPart() {
//        return test_boss;
//    }
//
//    @Override
//    public void setAngles(TestBossEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        this.getPart().traverse().forEach(ModelPart::resetTransform);
//        this.setHeadAngles(entity, netHeadYaw, headPitch, ageInTicks);
//
//        this.animateMovement(ModAnimations.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
//        this.updateAnimation(entity.idleAnimationState, ModAnimations.STAND, ageInTicks, 1f);
//        this.updateAnimation(entity.attackAnimationState, ModAnimations.ATTACK, ageInTicks, 1f);
//    }
//}