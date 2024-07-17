package kerkeruil.dope_dunes;

import kerkeruil.dope_dunes.entity.ModEntities;
import kerkeruil.dope_dunes.entity.client.TestBossModel;
import kerkeruil.dope_dunes.entity.client.TestBossRenderer;
import kerkeruil.dope_dunes.entity.layer.ModModelLayers;
import kerkeruil.dope_dunes.particle.ModParticles;
import kerkeruil.dope_dunes.particle.NuclearParticle;
import kerkeruil.dope_dunes.particle.PinkGarnetParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.util.ModelIdentifier;

public class DopeDunesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
//      Items
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> new ModelIdentifier(DopeDunes.MOD_ID, "blink_orb_3d", "inventory"));

//      Particles
        ParticleFactoryRegistry.getInstance().register(ModParticles.PINK_GARNET_PARTICLE, PinkGarnetParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.NUCLEAR_PARTICLE, NuclearParticle.Factory::new);

//      Entities
//        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.TEST_BOSS, TestBossModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.TEST_BOSS, TestBossRenderer::new);
    }
}
