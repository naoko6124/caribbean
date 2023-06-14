package caribbean.gui;

import caribbean.datastorage.DataStorage;
import caribbean.datastorage.Island;
import caribbean.datastructures.Fila;
import caribbean.datastructures.ListaNo;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.VerticalAlignment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.function.BiConsumer;

public class PathBookGui extends LightweightGuiDescription {
    WLabel lblNome;
    WLabel lblCoords;
    WButton btnSelecionar;
    WPlainPanel pnlIslands;
    WScrollPanel scrIslands;
    Island current;
    WWidget lastWgt;
    int atual = 1;
    public PathBookGui() {
        DataStorage.getInstance().rota = new Fila(5);
        DataStorage.getInstance().currentIsland = null;

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 160);
        WSprite icon = new WSprite(new Identifier("caribbean:textures/testebg.png"));
        root.add(icon, 0, 0, 17, 11);

        WLabel lblTitle = new WLabel(Text.of("Islands"), 0x000000);
        lblTitle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        root.add(lblTitle, 1, 1, 6, 1);

        lblNome = new WLabel(Text.of("Name: "));
        root.add(lblNome, 9, 1);
        lblCoords = new WLabel(Text.of("Coords: "));
        root.add(lblCoords, 9, 2);

        WLabel lblRota1 = new WLabel(Text.of("[1]"), 0x000000);
        root.add(lblRota1, 9, 5, 4, 1);
        WLabel lblRota2 = new WLabel(Text.of("[2]"), 0x000000);
        root.add(lblRota2, 9, 6, 4, 1);
        WLabel lblRota3 = new WLabel(Text.of("[3]"), 0x000000);
        root.add(lblRota3, 9, 7, 4, 1);
        WLabel lblRota4 = new WLabel(Text.of("[4]"), 0x000000);
        root.add(lblRota4, 9, 8, 4, 1);
        WLabel lblRota5 = new WLabel(Text.of("[5]"), 0x000000);
        root.add(lblRota5, 9, 9, 4, 1);

        btnSelecionar = new WButton(Text.of("Adicionar a Rota"));
        btnSelecionar.setEnabled(false);
        btnSelecionar.setOnClick(() -> {
            btnSelecionar.setEnabled(false);
            lblNome.setText(Text.of("Name: "));
            lblCoords.setText(Text.of("Coords: "));
            DataStorage.getInstance().rota.enfileirar(current);
            switch(atual)
            {
                case 1:
                    lblRota1.setText(Text.of("[1] " + current.name));
                    break;
                case 2:
                    lblRota2.setText(Text.of("[2] " + current.name));
                    break;
                case 3:
                    lblRota3.setText(Text.of("[3] " + current.name));
                    break;
                case 4:
                    lblRota4.setText(Text.of("[4] " + current.name));
                    break;
                case 5:
                    lblRota5.setText(Text.of("[5] " + current.name));
                    break;
            }
            atual++;
        });
        root.add(btnSelecionar, 9, 3, 6, 1);

        pnlIslands = new WPlainPanel();
        scrIslands = new WScrollPanel(pnlIslands);
        root.add(scrIslands, 1, 2, 6, 8);

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
                btnSelecionar.setEnabled(true);
                current = island;
            });
            lastWgt = btnExibe;
            pnlIslands.add(btnExibe, 0, height, 96, 1);
            height += 24;
        }
    }
    void ReloadRoute() {

    }
}