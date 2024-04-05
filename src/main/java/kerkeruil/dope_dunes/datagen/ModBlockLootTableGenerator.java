package kerkeruil.dope_dunes.datagen;

import kerkeruil.dope_dunes.block.ModBlocks;
import kerkeruil.dope_dunes.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.predicate.StatePredicate;

public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {
    public ModBlockLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.RADIOACTIVE_ORE, oreDrops(ModBlocks.RADIOACTIVE_ORE, ModItems.RADIOACTIVE_INGOT));
        addDrop(ModBlocks.RADIOACTIVE_ORE, oreDrops(ModBlocks.DEEPSLATE_RADIOACTIVE_ORE, ModItems.RADIOACTIVE_INGOT));
    }
}
