package caribbean.gui;

import caribbean.datastorage.DataStorage;
import caribbean.datastorage.Island;
import caribbean.datastructures.ListaEncadeada;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.text.Text;

import javax.xml.crypto.Data;

public class ShowIslandsGui extends LightweightGuiDescription {
    WLabel lblNome;
    WLabel lblCoords;
    WButton btnExcluir;
    WPlainPanel pnlIslands;
    WScrollPanel scrIslands;
    Island current;
    WWidget lastWgt;

    public ShowIslandsGui() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 160);

        WLabel lblTitle = new WLabel(Text.of("Islands"), 0x000000);
        root.add(lblTitle, 0, 0, 2, 1);

        lblNome = new WLabel(Text.of("Name: "));
        root.add(lblNome, 8, 1);
        lblCoords = new WLabel(Text.of("Coords: "));
        root.add(lblCoords, 8, 2);

        btnExcluir = new WButton(Text.of("Excluir"));
        btnExcluir.setEnabled(false);
        btnExcluir.setOnClick(() -> {
            DataStorage.getInstance().lista.excluiNo(current);
            pnlIslands.remove(lastWgt);
            ReloadIslands();
            btnExcluir.setEnabled(false);
            lblNome.setText(Text.of("Name: "));
            lblCoords.setText(Text.of("Coords: "));
        });
        root.add(btnExcluir, 8, 3, 2, 1);

        pnlIslands = new WPlainPanel();
        scrIslands = new WScrollPanel(pnlIslands);
        root.add(scrIslands, 0, 1, 6, 8);

        root.validate(this);

        ReloadIslands();
    }

    void ReloadIslands() {
        int height = 0;
        Object[] elementos = DataStorage.getInstance().lista.toArray();
        for (int i = 0; i < elementos.length; i++) {
            Island island = (Island)elementos[i];
            WButton btnExibe = new WButton(Text.of(island.name));
            btnExibe.setOnClick(() -> {
                lblNome.setText(Text.of("Name: " + island.name));
                lblCoords.setText(Text.of("Coords: (" + island.x + ", " + island.y + ", " + island.z + ")"));
                btnExcluir.setEnabled(true);
                current = island;
            });
            lastWgt = btnExibe;
            pnlIslands.add(btnExibe, 0, height, 96, 1);
            height += 24;
        }
    }
}
