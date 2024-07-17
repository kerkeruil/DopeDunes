package kerkeruil.dope_dunes.entity.custom;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.world.World;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.BowAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.AvoidSun;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.AvoidEntity;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.EscapeSun;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.StrafeTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetPlayerLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

/**
 * Example Skeleton using the SBL brain system
 */
public class TestBossEntity extends SkeletonEntity implements GeoEntity, SmartBrainOwner<TestBossEntity> {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public TestBossEntity(EntityType<? extends TestBossEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createTestBossAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0)
                .add(EntityAttributes.GENERIC_ARMOR, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK,2);
    }
    @Override
    protected void initGoals() {}
    // Let's make sure we're definitely not using any goals

    @Override
    public final void updateAttackType() {}

    @Override
    protected Brain.Profile<?> createBrainProfile() {
        return new SmartBrainProvider<>(this);
    }

    @Override
    public List<? extends ExtendedSensor<? extends TestBossEntity>> getSensors() {
        return ObjectArrayList.of(
                new NearbyPlayersSensor<>(), 							// Keep track of nearby players
                new NearbyLivingEntitySensor<TestBossEntity>()
                        .setPredicate((target, entity) ->
                                target instanceof PlayerEntity ||
                                        target instanceof IronGolemEntity ||
                                        target instanceof WolfEntity ||
                                        (target instanceof TurtleEntity turtle && turtle.isBaby() && !turtle.isInsideWaterOrBubbleColumn())));
    }																	// Keep track of nearby entities the Skeleton is interested in

    @Override
    public BrainActivityGroup<? extends TestBossEntity> getCoreTasks() {
        return BrainActivityGroup.coreTasks(
                new AvoidSun<>(),																							// Keep pathfinder avoiding the sun
                new EscapeSun<>().cooldownFor(entity -> 20),													// Escape the sun
                new AvoidEntity<>().avoiding(entity -> entity instanceof WolfEntity),												// Run away from wolves
                new LookAtTarget<>().runFor(entity -> entity.getRandom().nextBetween(40, 300)), 														// Look at the look target
                new StrafeTarget<>().stopStrafingWhen(entity -> !isHoldingBow(entity)).startCondition(TestBossEntity::isHoldingBow),	// Strafe around target
                new MoveToWalkTarget<>());																					// Move to the current walk target
    }

    @Override
    public BrainActivityGroup<? extends TestBossEntity> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<TestBossEntity>( 				// Run only one of the below behaviours, trying each one in order. Include explicit generic typing because javac is silly
                        new TargetOrRetaliate<>(),						// Set the attack target
                        new SetPlayerLookTarget<>(),					// Set the look target to a nearby player if available
                        new SetRandomLookTarget<>()), 					// Set the look target to a random nearby location
                new OneRandomBehaviour<>( 								// Run only one of the below behaviours, picked at random
                        new SetRandomWalkTarget<>().speedModifier(1), 				// Set the walk target to a nearby random pathable location
                        new Idle<>().runFor(entity -> entity.getRandom().nextBetween(30, 60)))); // Don't walk anywhere
    }

    @Override
    public BrainActivityGroup<? extends TestBossEntity> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>(), 	 // Invalidate the attack target if it's no longer applicable
                new FirstApplicableBehaviour<>( 																							  	 // Run only one of the below behaviours, trying each one in order
                        new BowAttack<>(20).startCondition(TestBossEntity::isHoldingBow),	 												 // Fire a bow, if holding one
                        new AnimatableMeleeAttack<>(10).whenStarting(entity -> setAttacking(true)).whenStarting(entity -> setAttacking(false))) // Melee attack
        );
    }
    @Override
    public void tick() {
        super.tick();
    }
    @Override
    protected void mobTick() {
        tickBrain(this);
    }

    // Easy predicate to save on redundant code
    private static boolean isHoldingBow(LivingEntity livingEntity) {
        return livingEntity.isHolding(stack -> stack.getItem() instanceof BowItem);
    }

    //    GECKOLIB STUFF
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, this::predicate));
    }
    private PlayState predicate(AnimationState<TestBossEntity> state) {
        if (state.isMoving()) {
            state.getController().setAnimation(RawAnimation.begin().then("animation.test_boss.walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        state.getController().setAnimation(RawAnimation.begin().then("animation.test_boss.stand", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }
}