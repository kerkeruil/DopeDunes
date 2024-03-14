package kerkeruil.dope_dunes.registry;

import kerkeruil.dope_dunes.DopeDunes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class ModBiomes {

//    public static final RegistryKey<Biome> SHADY_SANDS = register("shady_sands");

    public static final RegistryKey<Biome> HOT_RED = register("hot_red");
    public static final RegistryKey<Biome> COLD_BLUE = register("cold_blue");


    private static RegistryKey<Biome> register(String name)
    {
        return RegistryKey.of(RegistryKeys.BIOME, new Identifier(DopeDunes.MOD_ID, name));
    }
}
