package kerkeruil.dope_dunes.block;

import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.registry.ModItemGroup;
import kerkeruil.dope_dunes.registry.ModItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block PINK_GARNET_BLOCK = registerBlock("pink_garnet_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));

    public static final Block RADIOACTIVE_ORE = registerBlock("radioactive_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE), UniformIntProvider.create(3, 6)));

    public static final Block DEEPSLATE_RADIOACTIVE_ORE = registerBlock("deepslate_radioactive_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE), UniformIntProvider.create(3, 6)));


    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(DopeDunes.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(DopeDunes.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        ModItems.itemList.add(block);
        return Registry.register(Registries.ITEM, new Identifier(DopeDunes.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        DopeDunes.LOGGER.info("Registering ModBlocks for " + DopeDunes.MOD_ID);
    }
}
