package caribbean.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PirateBookGui extends LightweightGuiDescription {
    public PirateBookGui() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 160);

        WSprite icon = new WSprite(new Identifier("minecraft:textures/item/redstone.png"));
        root.add(icon, 0, 0, 1, 1);

        WLabel label = new WLabel(Text.of("Pirate Book"), 0x000000);
        root.add(label, 1, 0, 2, 1);

        WTextField txtEmail = new WTextField(Text.of("Email"));
        root.add(txtEmail, 0, 2, 6, 1);

        WButton button = new WButton(Text.of("Press"));
        button.setOnClick(() -> {
            String texto = txtEmail.getText();
            System.out.println(texto);
            label.setText(Text.of(texto));
        });
        root.add(button, 0, 4, 4, 1);

        root.validate(this);
    }
}
