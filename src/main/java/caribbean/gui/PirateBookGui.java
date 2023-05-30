package caribbean.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.VerticalAlignment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.function.BiConsumer;

public class PirateBookGui extends LightweightGuiDescription {
    public PirateBookGui() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 160);

        WLabel lblTitle = new WLabel(Text.of("Pirate Book"), 0x000000);
        root.add(lblTitle, 0, 0, 2, 1);

        Vec3d playerPos = MinecraftClient.getInstance().player.getPos();

        WTextField txtIslandName = new WTextField(Text.of("Island Name"));
        root.add(txtIslandName, 0, 1, 6, 1);

        WLabel lblX = new WLabel(Text.of("X"), 0x000000);
        lblX.setHorizontalAlignment(HorizontalAlignment.CENTER);
        lblX.setVerticalAlignment(VerticalAlignment.CENTER);
        root.add(lblX, 6, 1, 1, 1);

        WTextField txtX = new WTextField(Text.of("X"));
        txtX.setText(Math.round(playerPos.x) + "");
        root.add(txtX, 7, 1, 2, 1);

        WLabel lblY = new WLabel(Text.of("Y"), 0x000000);
        lblY.setHorizontalAlignment(HorizontalAlignment.CENTER);
        lblY.setVerticalAlignment(VerticalAlignment.CENTER);
        root.add(lblY, 9, 1, 1, 1);

        WTextField txtY = new WTextField(Text.of("Y"));
        txtY.setText(Math.round(playerPos.y) + "");
        root.add(txtY, 10, 1, 2, 1);

        WLabel lblZ = new WLabel(Text.of("Z"), 0x000000);
        lblZ.setHorizontalAlignment(HorizontalAlignment.CENTER);
        lblZ.setVerticalAlignment(VerticalAlignment.CENTER);
        root.add(lblZ, 12, 1, 1, 1);

        WTextField txtZ = new WTextField(Text.of("Z"));
        txtZ.setText(Math.round(playerPos.z) + "");
        root.add(txtZ, 13, 1, 2, 1);

        ArrayList<String> data = new ArrayList<>();
        data.add("Wolfram Alpha");
        data.add("Strange Home");

        BiConsumer<String, WPlainPanel> configurator = (String s, WPlainPanel p) -> {
            WButton btn = new WButton(Text.of(s));
            p.add(btn, 0, 0, 6, 1);
            p.setSize(6, 1);
        };
        WListPanel<String, WPlainPanel> list = new WListPanel<>(data, WPlainPanel::new, configurator);
        list.setListItemHeight(12);
        root.add(list, 0, 4, 7, 6);

        WButton button = new WButton(Text.of("Add"));
        button.setOnClick(() -> {
            String s = txtIslandName.getText();
            if (!s.isEmpty()) {
                data.add(s);
                list.layout();
            }
        });
        root.add(button, 0, 3, 4, 1);

        root.validate(this);
    }
}
