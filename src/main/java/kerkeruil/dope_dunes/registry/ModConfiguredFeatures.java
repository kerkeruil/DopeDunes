package kerkeruil.dope_dunes.registry;

import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

import java.util.List;


@SuppressWarnings("UnstableApiUsage")
public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> RADIOACTIVE_ORE_KEY = createRegistryKey("radioactive_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEEPSLATE_RADIOACTIVE_ORE_KEY = createRegistryKey("deepslate_radioactive_ore");

    public static void populate(FabricDynamicRegistryProvider.Entries entries) {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);

       entries.add(RADIOACTIVE_ORE_KEY, configureFeature(Feature.ORE, new OreFeatureConfig(stoneReplaceables, ModBlocks.RADIOACTIVE_ORE.getDefaultState(), 14)));
       entries.add(DEEPSLATE_RADIOACTIVE_ORE_KEY, configureFeature(Feature.ORE, new OreFeatureConfig(stoneReplaceables, ModBlocks.DEEPSLATE_RADIOACTIVE_ORE.getDefaultState(), 14)));


    }

    public static RegistryKey<ConfiguredFeature<?, ?>> createRegistryKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(DopeDunes.MOD_ID, name));
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, ?> configureFeature(F feature, FC config) {
        return new ConfiguredFeature<>(feature, config);
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}