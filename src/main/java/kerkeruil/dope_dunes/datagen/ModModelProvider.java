package kerkeruil.dope_dunes.datagen;

import kerkeruil.dope_dunes.block.ModBlocks;
import kerkeruil.dope_dunes.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;

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
    }
}
