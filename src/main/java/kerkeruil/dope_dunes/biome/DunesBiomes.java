package kerkeruil.dope_dunes.biome;


import kerkeruil.dope_dunes.registry.ModBiomes;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class DunesBiomes {
    public static Biome create(FabricDynamicRegistryProvider.Entries entries) {
        return new Biome.Builder()
                .generationSettings(createGenerationSettings(entries))
                .spawnSettings(createSpawnSettings())
                .precipitation(false)
                .temperature(0.9F)
                .downfall(0.1F)
                .effects(ModBiomes.createDefaultBiomeEffects()
                        .waterColor(0x4da5e3)
                        .waterFogColor(0x24a0b0)
                        .build()
                )
                .build();
    }

    private static GenerationSettings createGenerationSettings(FabricDynamicRegistryProvider.Entries entries) {
        GenerationSettings.LookupBackedBuilder builder = new GenerationSettings.LookupBackedBuilder(entries.placedFeatures(), entries.configuredCarvers());
        ModBiomes.addBasicFeatures(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addDefaultVegetation(builder);
        return builder.build();
    }

    private static SpawnSettings createSpawnSettings() {
        SpawnSettings.Builder builder = ModBiomes.createDefaultSpawnSettings();
        return builder.build();
    }
}