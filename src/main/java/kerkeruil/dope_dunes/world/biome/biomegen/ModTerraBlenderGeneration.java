package kerkeruil.dope_dunes.world.biome.biomegen;

import com.mojang.datafixers.util.Pair;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.world.biome.surfacebuilders.ModSurfaceBuilders;
import kerkeruil.dope_dunes.world.biome.surfacebuilders.ModSurfaceRules;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.*;

import java.util.function.Consumer;

import static kerkeruil.dope_dunes.registry.ModBiomes.LUSH_DESERT;


public class ModTerraBlenderGeneration extends Region implements Runnable, TerraBlenderApi {

    public ModTerraBlenderGeneration() {
        super(new Identifier(DopeDunes.MOD_ID, "overworld"), RegionType.OVERWORLD, 13);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        this.addBiomeSimilar(mapper, BiomeKeys.DESERT, LUSH_DESERT);

        // Balancing low-utilization areas with vanilla biomes.
        this.addBiomeSimilar(mapper, BiomeKeys.RIVER, BiomeKeys.RIVER);
        this.addBiomeSimilar(mapper, BiomeKeys.FROZEN_RIVER, BiomeKeys.FROZEN_RIVER);
        this.addBiomeSimilar(mapper, BiomeKeys.BEACH, BiomeKeys.BEACH);
        this.addBiomeSimilar(mapper, BiomeKeys.SNOWY_BEACH, BiomeKeys.SNOWY_BEACH);
        this.addBiomeSimilar(mapper, BiomeKeys.STONY_SHORE, BiomeKeys.STONY_SHORE);
        this.addBiomeSimilar(mapper, BiomeKeys.WARM_OCEAN, BiomeKeys.WARM_OCEAN);
        this.addBiomeSimilar(mapper, BiomeKeys.OCEAN, BiomeKeys.OCEAN);
        this.addBiomeSimilar(mapper, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.LUKEWARM_OCEAN);
        this.addBiomeSimilar(mapper, BiomeKeys.COLD_OCEAN, BiomeKeys.COLD_OCEAN);
        this.addBiomeSimilar(mapper, BiomeKeys.FROZEN_OCEAN, BiomeKeys.FROZEN_OCEAN);
        this.addBiomeSimilar(mapper, BiomeKeys.DEEP_OCEAN, BiomeKeys.DEEP_OCEAN);
        this.addBiomeSimilar(mapper, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN);
        this.addBiomeSimilar(mapper, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.DEEP_COLD_OCEAN);
        this.addBiomeSimilar(mapper, BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN);
        this.addBiomeSimilar(mapper, BiomeKeys.JUNGLE, BiomeKeys.JUNGLE);
        this.addBiomeSimilar(mapper, BiomeKeys.MANGROVE_SWAMP, BiomeKeys.MANGROVE_SWAMP);
        this.addBiomeSimilar(mapper, BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.BAMBOO_JUNGLE);
        this.addBiomeSimilar(mapper, BiomeKeys.SPARSE_JUNGLE, BiomeKeys.SPARSE_JUNGLE);
        this.addBiomeSimilar(mapper, BiomeKeys.SAVANNA, BiomeKeys.SAVANNA);
        this.addBiomeSimilar(mapper, BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.SAVANNA_PLATEAU);
        this.addBiomeSimilar(mapper, BiomeKeys.WINDSWEPT_SAVANNA, BiomeKeys.WINDSWEPT_SAVANNA);
        this.addBiomeSimilar(mapper, BiomeKeys.FROZEN_PEAKS, BiomeKeys.FROZEN_PEAKS);
        this.addBiomeSimilar(mapper, BiomeKeys.STONY_PEAKS, BiomeKeys.STONY_PEAKS);
        this.addBiomeSimilar(mapper, BiomeKeys.PLAINS, BiomeKeys.PLAINS);
        this.addBiomeSimilar(mapper, BiomeKeys.SUNFLOWER_PLAINS, BiomeKeys.SUNFLOWER_PLAINS);
        this.addBiomeSimilar(mapper, BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, BiomeKeys.WINDSWEPT_GRAVELLY_HILLS);
        this.addBiomeSimilar(mapper, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS);
        this.addBiomeSimilar(mapper, BiomeKeys.ICE_SPIKES, BiomeKeys.ICE_SPIKES);
        this.addBiomeSimilar(mapper, BiomeKeys.MUSHROOM_FIELDS, BiomeKeys.MUSHROOM_FIELDS);
    }

    @Override
    public void onTerraBlenderInitialized() {
        // We can't do registration stuff until both Terrestria and TerraBlender are ready.
        // The run() method below will be called when Terrestria is done initializing.
        DopeDunes.callbackWhenInitialized(ModSurfaceBuilders::init);
        DopeDunes.callbackWhenInitialized(this);
    }

    // Initialize TerraBlender as our biome placement provider.
    @Override
    public void run() {
        // Register the Terrestria surface rules; this must happen before we call addSurfaceRules().
        ModSurfaceRules.init();

        // Add the Terrestria Overworld surface rules via TerraBlender.
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, DopeDunes.MOD_ID, ModSurfaceRules.createRules());

        // Register the Terrestria surface builders.
        ModSurfaceBuilders.getBuilders().forEach(SurfaceGeneration::addSurfaceBuilder);

        // Add the biomes to Overworld generation via TerraBlender.
//        BIOME_CONFIG = Terrestria.getConfigManager().getBiomeConfig();
        Regions.register(this);
        Regions.register(new ModTerraBlenderRare());
    }
}