package caribbean.gui;

import caribbean.datastorage.DataStorage;
import caribbean.datastorage.Island;
import caribbean.datastructures.ListaNo;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.VerticalAlignment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.function.BiConsumer;

public class PathBookGui extends LightweightGuiDescription {
    public PathBookGui() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 160);

        WLabel lblTitle = new WLabel(Text.of("Path Book"), 0x000000);
        root.add(lblTitle, 0, 0, 2, 1);

        root.validate(this);
    }
}