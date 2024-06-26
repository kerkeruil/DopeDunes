package kerkeruil.dope_dunes.world.biome.surfacebuilders;


import com.mojang.serialization.Codec;
import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.registry.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.MaterialRules.MaterialRule;

import static net.minecraft.world.gen.surfacebuilder.MaterialRules.*;

public class ModSurfaceRules {
    public static MaterialRule createRules() {

        // Sandy surface rules
        MaterialRule sandAndSandstone = sequence(condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH,
                block(Blocks.SAND)), block(Blocks.SANDSTONE));

        // Biome-level rules
        MaterialRule lushDesert = condition(biome(ModBiomes.LUSH_DESERT),
                condition(noiseThreshold(NoiseParametersKeys.SURFACE, -0.75D), sandAndSandstone));

        MaterialRule dunes = condition(biome(ModBiomes.DUNES), sandAndSandstone);

        // Return a surface-only sequence of our surface rules
        return condition(surface(),
                sequence(dunes, lushDesert));
    }

    private static MaterialRule block(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }

    private static MaterialRules.MaterialCondition surfaceNoiseThreshold(double min) {
        return MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, min / 8.25, Double.MAX_VALUE);
    }

    public static void init() {
    }

    public static <T extends MaterialRule> Codec<T> register(String id, Codec<T> ruleCodec) {
        return Registry.register(Registries.MATERIAL_RULE, new Identifier(DopeDunes.MOD_ID, id), ruleCodec);
    }
}