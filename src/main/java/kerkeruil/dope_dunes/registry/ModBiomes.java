package kerkeruil.dope_dunes.registry;

import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.world.biome.DunesBiomes;
import kerkeruil.dope_dunes.world.biome.LushDesertBiomes;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;

@SuppressWarnings("UnstableApiUsage")
public class ModBiomes {
    public static final RegistryKey<Biome> DUNES = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(DopeDunes.MOD_ID, "dunes"));
    public static final RegistryKey<Biome> LUSH_DESERT = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(DopeDunes.MOD_ID, "lush_desert"));

    public static void populate(FabricDynamicRegistryProvider.Entries entries) {
        entries.add(DUNES, DunesBiomes.create(entries));
        entries.add(LUSH_DESERT, LushDesertBiomes.create(entries, false));
    }

    public static void addBasicFeatures(GenerationSettings.LookupBackedBuilder generationSettings) {
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
    }

    public static SpawnSettings.Builder createDefaultSpawnSettings() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        addDefaultCreatureSpawnEntries(spawnSettings);
        addDefaultAmbientSpawnEntries(spawnSettings);
        addDefaultMonsterSpawnEntries(spawnSettings);
        return spawnSettings;
    }

    public static void addDefaultCreatureSpawnEntries(SpawnSettings.Builder builder) {
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 12, 4, 4));
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 10, 4, 4));
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 10, 4, 4));
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 8, 4, 4));
    }

    public static void addDefaultAmbientSpawnEntries(SpawnSettings.Builder builder) {
        builder.spawn(SpawnGroup.AMBIENT, new SpawnSettings.SpawnEntry(EntityType.BAT, 10, 8, 8));
    }

    public static void addDefaultMonsterSpawnEntries(SpawnSettings.Builder builder) {
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 100, 4, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 4, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 100, 4, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 5, 1, 1));
    }

    // Copied from Traverse
    public static BiomeEffects.Builder createDefaultBiomeEffects() {
        return new BiomeEffects.Builder()
                .waterColor(0x3F76E4)
                .waterFogColor(0x50533)
                .skyColor(getSkyColor(0.2F))
                .fogColor(0xC0D8FF);
    }

    // Copied from Minecraft
    private static int getSkyColor(float temperature) {
        float f = temperature / 3.0F;
        f = MathHelper.clamp(f, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }

    public static void init() {
        // This just creates the registry keys.  Biome objects are requested and consumed by datagen now.
    }
}