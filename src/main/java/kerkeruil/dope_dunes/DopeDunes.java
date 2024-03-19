package kerkeruil.dope_dunes;

import kerkeruil.dope_dunes.biome.biomegen.ModBiolithGeneration;
import kerkeruil.dope_dunes.biome.surfacebuilders.ModSurfaceBuilders;
import kerkeruil.dope_dunes.util.ModRegistries;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

import java.util.ArrayList;

public class DopeDunes implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("dope_dunes");
	public static final String MOD_ID = "dope_dunes";

	private static Boolean initialized = false;
	private static final ArrayList<Runnable> runnables = new ArrayList<>(1);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Starting the dope dunes generation.");
		ModRegistries.registerModStuff();

		if (!FabricLoader.getInstance().isModLoaded("terrestria-worldgen")) {
			LOGGER.info("No Terrestria worldgen module present; Terrestria biomes will not generate.");
		}

		// At this point Terrestria is completely initialized.
		initialized = true;
		for (Runnable callback : runnables) {
			callback.run();
		}

		if (FabricLoader.getInstance().isModLoaded("biolith")) {
			LOGGER.info("Enabling Terrestria's Biolith worldgen module.");

			callbackWhenInitialized(ModSurfaceBuilders::init);
			callbackWhenInitialized(new ModBiolithGeneration());
		} else {
			LOGGER.warn("Terrestria world generation disabled; Biolith is not present.");
		}
	}

	public static void callbackWhenInitialized(Runnable callback) {
		if (initialized) {
			callback.run();
		} else {
			runnables.add(callback);
		}
	}
}
