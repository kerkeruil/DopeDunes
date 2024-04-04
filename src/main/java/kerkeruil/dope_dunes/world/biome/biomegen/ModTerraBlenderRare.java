package kerkeruil.dope_dunes.world.biome.biomegen;

import com.mojang.datafixers.util.Pair;
import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.registry.ModBiomes;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.*;

import java.util.function.Consumer;


public class ModTerraBlenderRare extends Region implements TerraBlenderApi {
    public ModTerraBlenderRare() {
        super(new Identifier(DopeDunes.MOD_ID, "overworld_rare"), RegionType.OVERWORLD, 7);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(BiomeKeys.DESERT, ModBiomes.DUNES);
        });
    }
}