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

    public static void populate(FabricDynamicRegistryProvider.Entries entries) {

    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        System.out.println("IS CALEED KERKERUIL");
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldRadioactiveOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.RADIOACTIVE_ORE.getDefaultState()));

        register(context, RADIOACTIVE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldRadioactiveOres, 12));
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

    public static void init() {
        // This just creates the registry keys.  Configured Features are requested and consumed by datagen now.
    }
}