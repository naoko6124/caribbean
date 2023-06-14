package caribbean.item;

import caribbean.datastorage.DataStorage;
import caribbean.datastorage.Island;
import caribbean.gui.PathBookGui;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class PirateCompass extends Item {
    public PirateCompass(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) {
            if (DataStorage.getInstance().rota != null && DataStorage.getInstance().currentIsland == null) {
                DataStorage.getInstance().currentIsland = (Island)DataStorage.getInstance().rota.desenfileirar();
            }
        }
        return super.use(world, user, hand);
    }
}
