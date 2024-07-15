package kerkeruil.dope_dunes.datagen;

import kerkeruil.dope_dunes.block.ModBlocks;
import kerkeruil.dope_dunes.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RADIOACTIVE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_RADIOACTIVE_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.RADIOACTIVE_INGOT, Models.GENERATED);

        // ARMOR
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.RADIOACTIVE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.RADIOACTIVE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.RADIOACTIVE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.RADIOACTIVE_BOOTS));

        // SPAWN EGGS
        itemModelGenerator.register(ModItems.TEST_BOSS_EGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));
    }
}
