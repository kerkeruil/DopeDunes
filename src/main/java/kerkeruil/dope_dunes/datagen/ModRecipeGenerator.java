package kerkeruil.dope_dunes.datagen;

import kerkeruil.dope_dunes.block.ModBlocks;
import kerkeruil.dope_dunes.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {

//        offerSmelting(exporter, List.of(ModBlocks.RADIOACTIVE_ORE), RecipeCategory.MISC, ModItems.RADIOACTIVE_INGOT,
//                0.25f, 200, "radioactive_ingot");
//        offerBlasting(exporter, List.of(ModBlocks.RADIOACTIVE_ORE), RecipeCategory.MISC, ModItems.RADIOACTIVE_INGOT,
//                0.25f, 200, "radioactive_ingot");
    }
}
