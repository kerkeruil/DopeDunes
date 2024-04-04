//package kerkeruil.dope_dunes.world;
//
//import kerkeruil.dope_dunes.DopeDunes;
//import kerkeruil.dope_dunes.block.ModBlocks;
//import net.minecraft.block.Blocks;
//import net.minecraft.registry.Registerable;
//import net.minecraft.registry.RegistryKey;
//import net.minecraft.registry.RegistryKeys;
//import net.minecraft.registry.tag.BlockTags;
//import net.minecraft.structure.rule.BlockMatchRuleTest;
//import net.minecraft.structure.rule.RuleTest;
//import net.minecraft.structure.rule.TagMatchRuleTest;
//import net.minecraft.util.Identifier;
//import net.minecraft.util.math.intprovider.ConstantIntProvider;
//import net.minecraft.util.math.intprovider.UniformIntProvider;
//import net.minecraft.world.gen.feature.*;
//import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
//import net.minecraft.world.gen.stateprovider.BlockStateProvider;
//
//import java.util.List;
//
//public class ModConfiguredFeatures {
//    public static final RegistryKey<ConfiguredFeature<?, ?>> RADIOACTIVE_ORE_KEY = registerKey("radioactive_ore");
//
//    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
//        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
//
//        List<OreFeatureConfig.Target> overworldRadioactiveOres =
//                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.RADIOACTIVE_ORE.getDefaultState()));
//
//        register(context, RADIOACTIVE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldRadioactiveOres, 12));
//    }
//
//
//    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
//        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(DopeDunes.MOD_ID, name));
//    }
//
//    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
//                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
//        context.register(key, new ConfiguredFeature<>(feature, configuration));
//    }
//}