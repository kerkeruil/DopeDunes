package kerkeruil.dope_dunes.world;

import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.block.ModBlocks;
import kerkeruil.dope_dunes.registry.ModConfiguredFeatures;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class ModPlacedFeatures {
    private static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_OASIS_VEGETATION_CONFIGURED = kerkeruil.dope_dunes.registry.ModConfiguredFeatures.createRegistryKey("patch_oasis_vegetation");
    public static final RegistryKey<PlacedFeature> PATCH_OASIS_VEGETATION = createRegistryKey("patch_oasis_vegetation");
    private static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_LUSH_DESERT_VEGETATION_CONFIGURED = kerkeruil.dope_dunes.registry.ModConfiguredFeatures.createRegistryKey("patch_lush_desert_vegetation");
    public static final RegistryKey<PlacedFeature> PATCH_LUSH_DESERT_VEGETATION = createRegistryKey("patch_lush_desert_vegetation");

    public static final RegistryKey<PlacedFeature> RADIOACTIVE_ORE_PLACED_KEY = createRegistryKey("radioactive_ore_placed");


    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    public static RegistryKey<PlacedFeature> createRegistryKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(DopeDunes.MOD_ID, name));
    }

    public static void populate(FabricDynamicRegistryProvider.Entries entries) {
        final BlockPredicate ON_DIRT = BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), BlockTags.DIRT);
        final BlockPredicate ON_SAND = BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), BlockTags.SAND);
        final BlockPredicate ON_DIRT_OR_SAND = BlockPredicate.eitherOf(ON_DIRT, ON_SAND);

        // Terrestria Decorated Features
        entries.add(PATCH_OASIS_VEGETATION_CONFIGURED, kerkeruil.dope_dunes.registry.ModConfiguredFeatures.configureFeature(Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(32, 15, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
                                new WeightedBlockStateProvider(createStatePoolBuilder()
                                        .add(Blocks.FERN.getDefaultState(), 1)
                                        .add(Blocks.GRASS.getDefaultState(), 2)
                                        .add(Blocks.DANDELION.getDefaultState(), 4)
//                                        .add(TerrestriaBlocks.AGAVE.getDefaultState(), 1)
//                                        .add(TerrestriaBlocks.ALOE_VERA.getDefaultState(), 1)
                                        .build())),
                        BlockPredicate.IS_AIR))));

        entries.add(PATCH_OASIS_VEGETATION, placeFeature(entries, PATCH_OASIS_VEGETATION_CONFIGURED,
                CountPlacementModifier.of(6),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND)));

        entries.add(PATCH_LUSH_DESERT_VEGETATION_CONFIGURED, ModConfiguredFeatures.configureFeature(Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(32, 15, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
                                new WeightedBlockStateProvider(createStatePoolBuilder()
                                        .add(Blocks.DEAD_BUSH.getDefaultState(), 1)

//                                        .add(TerrestriaBlocks.TINY_CACTUS.getDefaultState(), 1)
                                        .build())),
                        BlockPredicate.IS_AIR))));
        entries.add(PATCH_LUSH_DESERT_VEGETATION, placeFeature(entries, PATCH_LUSH_DESERT_VEGETATION_CONFIGURED,
                CountPlacementModifier.of(4),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND)));

        entries.add(RADIOACTIVE_ORE_PLACED_KEY, placeFeature(entries, ModConfiguredFeatures.RADIOACTIVE_ORE_KEY,
                ModOrePlacement.modifiersWithCount(10, // Veins per Chunk
                        // Distribution between -80 and 80
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80)))
        ));
//
//        register(context, RADIOACTIVE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(RADIOACTIVE_ORE_KEY),
//                ModOrePlacement.modifiersWithCount(10, // Veins per Chunk
//                        // Distribution between -80 and 80
//                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80))));

    }

    private static DataPool.Builder<BlockState> createStatePoolBuilder() {
        return DataPool.builder();
    }

    private static PlacedFeature placeFeature(FabricDynamicRegistryProvider.Entries entries, RegistryKey<ConfiguredFeature<?, ?>> feature, PlacementModifier... placementModifiers) {
        List<PlacementModifier> list = new ArrayList<>(List.of(placementModifiers));
        list.add(BiomePlacementModifier.of());
        return placeFeature(entries, feature, list);
    }

    private static PlacedFeature placeFeature(FabricDynamicRegistryProvider.Entries entries, RegistryKey<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> list) {
        return new PlacedFeature(entries.ref(feature), list);
    }
}
