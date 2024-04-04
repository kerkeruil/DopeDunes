package kerkeruil.dope_dunes.world.biome.biomegen;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.world.biome.surfacebuilders.ModSurfaceBuilders;
import kerkeruil.dope_dunes.world.biome.surfacebuilders.ModSurfaceRules;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;

import static kerkeruil.dope_dunes.registry.ModBiomes.DUNES;
import static kerkeruil.dope_dunes.registry.ModBiomes.LUSH_DESERT;

public class ModBiolithGeneration implements Runnable {

    public void addBiomes() {

        BiomePlacement.replaceOverworld(BiomeKeys.DESERT, DUNES, 0.175D);
        BiomePlacement.replaceOverworld(BiomeKeys.DESERT, LUSH_DESERT, 0.325D);
    }

    // Use Biolith to register our Biome placements.
    // We can't do registration stuff until Terrestria's common module is ready.
    // This method will be called when Terrestria is done initializing.
    @Override
    public void run() {
        // Register the Terrestria surface rules.
        SurfaceGeneration.addOverworldSurfaceRules(
                Identifier.of(DopeDunes.MOD_ID, "surface_rules"),
                ModSurfaceRules.createRules());

        // Register the Terrestria surface builders.
        ModSurfaceBuilders.getBuilders().forEach(SurfaceGeneration::addSurfaceBuilder);

        // Add the biomes to Overworld generation via Biolith.
        this.addBiomes();
    }
}