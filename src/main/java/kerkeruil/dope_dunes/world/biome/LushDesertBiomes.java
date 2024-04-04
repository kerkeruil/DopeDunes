package kerkeruil.dope_dunes.world.biome;

import kerkeruil.dope_dunes.registry.ModBiomes;
import kerkeruil.dope_dunes.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;


public class LushDesertBiomes {
    public static Biome create(FabricDynamicRegistryProvider.Entries entries, boolean oasis) {
        return new Biome.Builder()
                .generationSettings(createGenerationSettings(entries, oasis))
                .spawnSettings(createSpawnSettings())
                .precipitation(true)
                .temperature(0.7F)
                .downfall(0.7F)
                .effects(ModBiomes.createDefaultBiomeEffects()
                        .waterColor(0x3f76e4)
                        .waterFogColor(0x50533)
                        .build()
                )
                .build();
    }

    private static GenerationSettings createGenerationSettings(FabricDynamicRegistryProvider.Entries entries, boolean oasis) {
        GenerationSettings.LookupBackedBuilder builder = new GenerationSettings.LookupBackedBuilder(entries.placedFeatures(), entries.configuredCarvers());
        ModBiomes.addBasicFeatures(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);
//        if (oasis) {
//            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(TerrestriaPlacedFeatures.JUNGLE_PALM_TREES));
//        } else {
//            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(TerrestriaPlacedFeatures.RARE_YUCCA_PALM_TREES));
//            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(TerrestriaPlacedFeatures.SAGUARO_CACTUSES));
//        }
        DefaultBiomeFeatures.addDefaultFlowers(builder);
        DefaultBiomeFeatures.addDefaultGrass(builder);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        if (oasis) {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(ModPlacedFeatures.PATCH_OASIS_VEGETATION));
        } else {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(ModPlacedFeatures.PATCH_LUSH_DESERT_VEGETATION));
        }
        DefaultBiomeFeatures.addDefaultVegetation(builder);
        return builder.build();
    }

    private static SpawnSettings createSpawnSettings() {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        ModBiomes.addDefaultAmbientSpawnEntries(builder);
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 2, 3));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 100, 4, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 4, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 100, 4, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 5, 1, 1));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 19, 4, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 1, 1, 1));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HUSK, 80, 4, 4));
        return builder.build();
    }
}