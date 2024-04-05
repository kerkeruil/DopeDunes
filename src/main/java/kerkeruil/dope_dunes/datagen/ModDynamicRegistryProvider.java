package kerkeruil.dope_dunes.datagen;


import kerkeruil.dope_dunes.registry.ModBiomes;
import kerkeruil.dope_dunes.registry.ModConfiguredFeatures;
import kerkeruil.dope_dunes.world.ModPlacedFeatures;
import kerkeruil.dope_dunes.world.dimension.ModDimensions;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("UnstableApiUsage")
public class ModDynamicRegistryProvider extends FabricDynamicRegistryProvider {
    public ModDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {

        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.DIMENSION_TYPE));

        ModConfiguredFeatures.populate(entries);
        ModPlacedFeatures.populate(entries);
        ModBiomes.populate(entries);

    }

    @Override
    public String getName() {
        return "DopeDunes";
    }
}