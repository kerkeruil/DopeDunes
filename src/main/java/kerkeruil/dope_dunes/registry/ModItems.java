package kerkeruil.dope_dunes.registry;

import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.entity.ModEntities;
import kerkeruil.dope_dunes.item.BlinkOrbItem;
import kerkeruil.dope_dunes.item.ModArmorItem;
import kerkeruil.dope_dunes.item.ModArmorMaterials;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.ArrayList;
import java.util.logging.Logger;

public class ModItems {
    public static ArrayList<Object> itemList = new ArrayList<>();

    public static final Item RADIOACTIVE_INGOT = registerItem("radioactive_ingot",
            new Item(new FabricItemSettings()));

    public static final Item BLINK_ORB = registerItem("blink_orb",
            new BlinkOrbItem(new FabricItemSettings().maxDamage(100)));

//  Armor
    public static final Item RADIOACTIVE_HELMET = registerItem("radioactive_helmet",
            new ModArmorItem(ModArmorMaterials.RADIOACTIVE_INGOT, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item RADIOACTIVE_CHESTPLATE = registerItem("radioactive_chestplate",
            new ModArmorItem(ModArmorMaterials.RADIOACTIVE_INGOT, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item RADIOACTIVE_LEGGINGS = registerItem("radioactive_leggings",
            new ModArmorItem(ModArmorMaterials.RADIOACTIVE_INGOT, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item RADIOACTIVE_BOOTS = registerItem("radioactive_boots",
            new ModArmorItem(ModArmorMaterials.RADIOACTIVE_INGOT, ArmorItem.Type.BOOTS, new FabricItemSettings()));

//  Spawn Eggs
    public static final Item TEST_BOSS_EGG = registerItem("test_boss_egg",
        new SpawnEggItem(ModEntities.TEST_BOSS, 0x727272, 0x03c90e, new FabricItemSettings()));

    public static void registerModItems() {
        DopeDunes.LOGGER.info("Registering " + itemList.size() + " Mod Items for " + DopeDunes.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.DOPE_DUNES_GROUP_KEY).register(ModItems::itemGroupDopeDunes);
    }

    private static Item registerItem(String name, Item item) {
        itemList.add(item);
        return Registry.register(Registries.ITEM, new Identifier(DopeDunes.MOD_ID, name), item);
    }

    private static void itemGroupDopeDunes(FabricItemGroupEntries entries) {
        for(Object item : itemList){
            if(item instanceof Item){
                entries.add((Item) item);
            } else if(item instanceof Block){
                entries.add((Block) item);
            } else if(item instanceof ItemStack){
                entries.add((ItemStack) item);
            } else{
                DopeDunes.LOGGER.info("Couldn't recognize type in itemlist: " + item.getClass() + " " + item);
            }
        }
    }
}
