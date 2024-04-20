package kerkeruil.dope_dunes.registry;

import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.effect.ModPotions;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static kerkeruil.dope_dunes.effect.ModPotions.*;


public class ModItemGroup {
    public static final RegistryKey<ItemGroup> DOPE_DUNES_GROUP_KEY =
            RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(DopeDunes.MOD_ID, "dope_dunes_group"));

    public static final ItemGroup DOPE_DUNES_GROUP = Registry.register(
            Registries.ITEM_GROUP, DOPE_DUNES_GROUP_KEY,
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.dope_dunes_group"))
                    .icon(() -> new ItemStack(ModItems.RADIOACTIVE_INGOT)).entries((displayContext, entries) ->
                    {
                        entries.add(SlimeyPotionStack);
                        entries.add(SlimeyLingeringPotionStack);
                        entries.add(SlimeySplashPotionStack);
                        /*Items are added via the item class*/
                    })
                    .build());
    public static void registerItemGroup(){
    }
}
