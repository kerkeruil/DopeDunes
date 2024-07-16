package kerkeruil.dope_dunes.entity.ai;

import kerkeruil.dope_dunes.entity.custom.TestBossEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.Hand;

public class TestBossBasicAttackGoal extends MeleeAttackGoal {
    private final TestBossEntity entity;
    private final float AttackRange = 4f;

    private int attackDelay = 10;
    private int ticksUntilNextAttack = 15;
    private boolean shouldCountTillNextAttack = false;

    public TestBossBasicAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
        entity = ((TestBossEntity) mob);
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 10;
        ticksUntilNextAttack = 15;
    }

    @Override
    protected void attack(LivingEntity pEnemy) {
        if (isEnemyWithinAttackDistance(pEnemy)) {
            shouldCountTillNextAttack = true;

            if(isTimeToStartAttackAnimation()) {
                entity.setAttacking(true);
            }

            if(isTimeToAttack()) {
                this.mob.getLookControl().lookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
                performAttack(pEnemy);
            }
        } else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
//            entity.attackAnimationTimeout = 0;
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy) {
        return this.mob.squaredDistanceTo(pEnemy) < AttackRange;
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.getTickCount(attackDelay);
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected void performAttack(LivingEntity pEnemy) {
        this.resetAttackCooldown();
        this.mob.swingHand(Hand.MAIN_HAND);
        this.mob.tryAttack(pEnemy);
    }

    @Override
    public void tick() {
        super.tick();
        if(shouldCountTillNextAttack) {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }
    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }
}

