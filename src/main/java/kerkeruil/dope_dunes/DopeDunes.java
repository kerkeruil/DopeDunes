package kerkeruil.dope_dunes;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class DopeDunes implements ModInitializer, TerraBlenderApi {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("dope-dunes");
	public static final String MOD_ID = "dope_dunes";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Starting the dope dunes generation.");
	}


	@Override
	public void onTerraBlenderInitialized()
	{
		// Weights are kept intentionally low as we add minimal biomes
		Regions.register(new TestRegion1(new Identifier(MOD_ID, "overworld_1"), 2));
		Regions.register(new TestRegion2(new Identifier(MOD_ID, "overworld_2"), 2));

		// Register our surface rules
		SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, TestSurfaceRuleData.makeRules());
	}
}