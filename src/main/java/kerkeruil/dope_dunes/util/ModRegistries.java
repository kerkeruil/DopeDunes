package kerkeruil.dope_dunes.util;

import kerkeruil.dope_dunes.DopeDunes;
import kerkeruil.dope_dunes.block.ModBlocks;
import kerkeruil.dope_dunes.particle.ModParticles;
import kerkeruil.dope_dunes.registry.ModBiomes;
import kerkeruil.dope_dunes.registry.ModItemGroup;
import kerkeruil.dope_dunes.registry.ModItems;
import kerkeruil.dope_dunes.world.ModOreGeneration;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ModRegistries {
    public static void registerModStuff(){
        ModBiomes.init();

        ModItemGroup.registerItemGroup();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModOreGeneration.generateOres();
        ModParticles.registerParticles();
    }

    private static void createPortal() {
        CustomPortalBuilder.beginPortal()
                .frameBlock(Blocks.RED_SAND)
                .lightWithItem(Items.FLINT_AND_STEEL)
                .destDimID(new Identifier(DopeDunes.MOD_ID, "dope_dunes"))
                .tintColor(0xc76efa)
                .registerPortal();
    }

}
