package kerkeruil.dope_dunes.entity;

import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.entity.custom.TestBossEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<TestBossEntity> TEST_BOSS = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(DopeDunes.MOD_ID, "test_boss"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, TestBossEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 2.3f)).build());



    public static void registerModEntities() {
        DopeDunes.LOGGER.info("Registering Mod Entities for " + DopeDunes.MOD_ID);
    }
}
