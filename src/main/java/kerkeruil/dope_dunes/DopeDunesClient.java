package kerkeruil.dope_dunes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.client.util.ModelIdentifier;

public class DopeDunesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> new ModelIdentifier(DopeDunes.MOD_ID, "blink_orb_3d", "inventory"));
    }
}
