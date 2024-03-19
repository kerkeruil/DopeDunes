package kerkeruil.dope_dunes.registry;

import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.biome.region.ShadySandsRegion;
import kerkeruil.dope_dunes.biome.region.TestRegion1;
import kerkeruil.dope_dunes.biome.region.TestRegion2;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import terrablender.api.Regions;

public class ModBiomes {

    public static final RegistryKey<Biome> SHADY_SANDS = register("shady_sands");

    // Examples
    public static final RegistryKey<Biome> HOT_RED = register("hot_red");
    public static final RegistryKey<Biome> COLD_BLUE = register("cold_blue");


    public static void registerRegions() {
        Regions.register(new ShadySandsRegion(new Identifier(DopeDunes.MOD_ID, "shady_sands"), 20));

        // EXAMPLE Weights are kept intentionally low as we add minimal biomes
        Regions.register(new TestRegion1(new Identifier(DopeDunes.MOD_ID, "overworld_1"), 2));
        Regions.register(new TestRegion2(new Identifier(DopeDunes.MOD_ID, "overworld_2"), 2));
    }

    private static RegistryKey<Biome> register(String name)
    {
        return RegistryKey.of(RegistryKeys.BIOME, new Identifier(DopeDunes.MOD_ID, name));
    }
}
