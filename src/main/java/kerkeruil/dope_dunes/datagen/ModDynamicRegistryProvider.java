package kerkeruil.dope_dunes.datagen;


import kerkeruil.dope_dunes.registry.ModBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("UnstableApiUsage")
public class ModDynamicRegistryProvider extends FabricDynamicRegistryProvider {
    public ModDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
//        TerrestriaConfiguredFeatures.populate(entries);
//        TerrestriaPlacedFeatures.populate(entries);
        ModBiomes.populate(entries);
    }

    @Override
    public String getName() {
        return "Terrestria";
    }
}