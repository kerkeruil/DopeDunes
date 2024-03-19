package kerkeruil.dope_dunes.biome.surfacebuilders;


import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.registry.ModBiomes;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class ModSurfaceBuilders {
    private static final HashMap<Identifier, BiolithSurfaceBuilder> builders = new HashMap<>(8);

    public static void init() {
        builders.put(Identifier.of(DopeDunes.MOD_ID, "surface/dunes"),
                new DuneSurfaceBuilder(Blocks.SAND.getDefaultState()).setBiomeKey(ModBiomes.DUNES));
    }

    public static HashMap<Identifier, BiolithSurfaceBuilder> getBuilders() {
        return builders;
    }
}