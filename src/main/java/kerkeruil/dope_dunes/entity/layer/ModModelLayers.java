package kerkeruil.dope_dunes.entity.layer;

import kerkeruil.dope_dunes.DopeDunes;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer TEST_BOSS =
            new EntityModelLayer(new Identifier(DopeDunes.MOD_ID, "test_boss"), "main");
}
