package kerkeruil.dope_dunes;

import kerkeruil.dope_dunes.particle.ModParticles;
import kerkeruil.dope_dunes.particle.NuclearParticle;
import kerkeruil.dope_dunes.particle.PinkGarnetParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.util.ModelIdentifier;

public class DopeDunesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> new ModelIdentifier(DopeDunes.MOD_ID, "blink_orb_3d", "inventory"));

        ParticleFactoryRegistry.getInstance().register(ModParticles.PINK_GARNET_PARTICLE, PinkGarnetParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.NUCLEAR_PARTICLE, NuclearParticle.Factory::new);

    }
}
