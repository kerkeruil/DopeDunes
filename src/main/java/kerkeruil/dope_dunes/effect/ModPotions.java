package kerkeruil.dope_dunes.effect;

import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.registry.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static final Potion SLIMEY_POTION = registerPotion("slimey_potion",
            new Potion(new StatusEffectInstance(ModEffects.SLIMEY, 200, 0)));

//    public static final ItemStack SlimeyPotionStack = PotionUtil.setPotion(new ItemStack(Items.POTION), SLIMEY_POTION);
//    public static final ItemStack SlimeyLingeringPotionStack = PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), SLIMEY_POTION);
//    public static final ItemStack SlimeySplashPotionStack = PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), SLIMEY_POTION);

    private static Potion registerPotion(String name, Potion potion) {
        Potion registeredPotion = Registry.register(Registries.POTION, new Identifier(DopeDunes.MOD_ID, name), potion);
        addToItemList(registeredPotion);
        return registeredPotion;
    }

    private static void addToItemList(Potion registeredPotion) {
        final ItemStack regularPotion = PotionUtil.setPotion(new ItemStack(Items.POTION), registeredPotion);
        final ItemStack lingeringPotion = PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), registeredPotion);
        final ItemStack splashPotion = PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), registeredPotion);
        ModItems.itemList.add(regularPotion);
        ModItems.itemList.add(lingeringPotion);
        ModItems.itemList.add(splashPotion);
    }

    public static void registerPotions() {
        DopeDunes.LOGGER.info("Registering Potions for " + DopeDunes.MOD_ID);
    }
}
