/**
 * Copyright (C) Glitchfiend
 * <p>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package kerkeruil.dope_dunes.biome;

import kerkeruil.dope_dunes.registry.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class ModSurfaceRuleData
{
    private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule RED_TERRACOTTA = makeStateRule(Blocks.RED_TERRACOTTA);
    private static final MaterialRules.MaterialRule BLUE_TERRACOTTA = makeStateRule(Blocks.BLUE_TERRACOTTA);

    private static final MaterialRules.MaterialRule SAND = makeStateRule(Blocks.SAND);
    private static final MaterialRules.MaterialRule SAND_STONE = makeStateRule(Blocks.SANDSTONE);
    private static final MaterialRules.MaterialRule MAGMA_BLOCK = makeStateRule(Blocks.MAGMA_BLOCK);



    public static MaterialRules.MaterialRule makeRules()
    {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);
        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        return MaterialRules.sequence(
            MaterialRules.condition(MaterialRules.biome(ModBiomes.HOT_RED), RED_TERRACOTTA),
            MaterialRules.condition(MaterialRules.biome(ModBiomes.COLD_BLUE), BLUE_TERRACOTTA),

            MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(ModBiomes.SHADY_SANDS),
                            MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, SAND)),
                            MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, SAND_STONE),
                            MaterialRules.condition(MaterialRules.hole(), MAGMA_BLOCK)
            ),



            // Default to a grass and dirt surface
            MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface)
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block)
    {
        return MaterialRules.block(block.getDefaultState());
    }
}
