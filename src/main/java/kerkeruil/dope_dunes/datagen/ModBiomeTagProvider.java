package kerkeruil.dope_dunes.datagen;


import kerkeruil.dope_dunes.registry.ModBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagProvider extends FabricTagProvider<Biome> {
    public ModBiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BIOME, registriesFuture);
    }

    @Override
    public void configure(RegistryWrapper.WrapperLookup registries) {
        /*
         * Vanilla biome categories
         */
        getOrCreateTagBuilder(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS)
                .addOptional(ModBiomes.DUNES);

        getOrCreateTagBuilder(BiomeTags.SPAWNS_WARM_VARIANT_FROGS)
                .addOptional(ModBiomes.LUSH_DESERT)
                .addOptional(ModBiomes.DUNES);



        /*
         * Common biome categories
         */

//        getOrCreateTagBuilder(ConventionalBiomeTags.DEAD)
//                .addOptional(ModBiomes.DUNES);

        getOrCreateTagBuilder(ConventionalBiomeTags.DESERT)
                .addOptional(ModBiomes.LUSH_DESERT)
                .addOptional(ModBiomes.DUNES);

        getOrCreateTagBuilder(ConventionalBiomeTags.IN_OVERWORLD)
                .addOptional(ModBiomes.LUSH_DESERT)
                .addOptional(ModBiomes.DUNES);

        /*
         * Biome structure generation tags
         */
        getOrCreateTagBuilder(BiomeTags.DESERT_PYRAMID_HAS_STRUCTURE)
                .addOptional(ModBiomes.LUSH_DESERT)
                .addOptional(ModBiomes.DUNES);

        getOrCreateTagBuilder(BiomeTags.MINESHAFT_HAS_STRUCTURE)
                .addOptional(ModBiomes.LUSH_DESERT)
                .addOptional(ModBiomes.DUNES);

        getOrCreateTagBuilder(BiomeTags.RUINED_PORTAL_DESERT_HAS_STRUCTURE)
                .addOptional(ModBiomes.LUSH_DESERT)
                .addOptional(ModBiomes.DUNES);


        getOrCreateTagBuilder(BiomeTags.STRONGHOLD_HAS_STRUCTURE)
                .addOptional(ModBiomes.LUSH_DESERT)
                .addOptional(ModBiomes.DUNES);

        getOrCreateTagBuilder(BiomeTags.VILLAGE_DESERT_HAS_STRUCTURE)
                .addOptional(ModBiomes.LUSH_DESERT)
                .addOptional(ModBiomes.DUNES);

    }
}