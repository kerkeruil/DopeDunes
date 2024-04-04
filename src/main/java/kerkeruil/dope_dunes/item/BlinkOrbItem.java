package kerkeruil.dope_dunes.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BlinkOrbItem extends Item {
    public BlinkOrbItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        // Set sound and cooldown
        ItemStack itemstack = player.getStackInHand(hand);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.NEUTRAL,1.5F, 1F);
        player.getItemCooldownManager().set(this, 40);

        // "Teleportation"
        Vec3d playerPos = player.getPos();
        for(int x = 0; x < 18; ++x) {
            for(int y = 0; y < 18; ++y) {
                world.addParticle(ParticleTypes.POOF,
                playerPos.getX(), playerPos.getY(), playerPos.getZ(),
                Math.cos(1*20) * 0.15d, Math.cos(1*20) * 0.15d, Math.sin(1*20) * 0.15d);
            }
        }
        player.setVelocity(player.getVelocity().multiply(15));

        // Handle durability
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!player.getAbilities().creativeMode) {
            itemstack.damage(1, player, p -> p.sendToolBreakStatus(hand));
        }

        return TypedActionResult.success(itemstack, world.isClient());
    }
}
