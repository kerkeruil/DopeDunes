package kerkeruil.dope_dunes.biome;

import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.biome.regions.TestRegion1;
import kerkeruil.dope_dunes.biome.regions.TestRegion2;
import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerraBlenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized()
    {
        // Weights are kept intentionally low as we add minimal biomes
        Regions.register(new TestRegion1(new Identifier(DopeDunes.MOD_ID, "overworld_1"), 2));
        Regions.register(new TestRegion2(new Identifier(DopeDunes.MOD_ID, "overworld_2"), 2));

        // Register our surface rules
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, DopeDunes.MOD_ID, ModSurfaceRuleData.makeRules());
    }
}
