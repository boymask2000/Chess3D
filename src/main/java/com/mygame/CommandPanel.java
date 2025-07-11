package com.mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.scene.Node;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.style.BaseStyles;

/**
 *
 * @author giovanni
 */
public class CommandPanel {

    private ScreenCommand currentCommand;

    public ScreenCommand getCurrentCommand() {
        return currentCommand;
    }

    public void reset() {
        currentCommand = null;
    }

    public CommandPanel(Node guiNode) {
    
        // Creazione di un contenitore (pannello verticale)
        Container panel = new Container();
        panel.setLocalTranslation(10, Pool.getCam().getHeight() - 10, 0);
    //    panel.setLocalTranslation(300, 400, 0); // posizione sullo schermo
        guiNode.attachChildAt(panel, 0);

        Button b_create_falegname = panel.addChild(new Button("Falegname"));
        Button b_create_taglialegna = panel.addChild(new Button("Taglialegna"));
        Button b_create_forestale = panel.addChild(new Button("Forestale"));
        Button b_create_pozzo = panel.addChild(new Button("Pozzo"));
        Button b_create_deposito = panel.addChild(new Button("Deposito"));
        Button b_create_macellaio = panel.addChild(new Button("Macellaio"));
        Button b_create_forno = panel.addChild(new Button("Forno"));
        Button b_create_mulino = panel.addChild(new Button("Mulino"));
        Button b_create_allevatore_maiali = panel.addChild(new Button("Fattoria Maiali"));
        Button b_create_coltivatore_grano = panel.addChild(new Button("Fattoria Grano"));

        b_create_coltivatore_grano.addClickCommands(source -> {
            currentCommand = ScreenCommand.CREATE_COLTIVATORE_GRANO;
        });
        b_create_allevatore_maiali.addClickCommands(source -> {
            currentCommand = ScreenCommand.CREATE_ALLEVATORE_MAIALI;
        });
        b_create_mulino.addClickCommands(source -> {
            currentCommand = ScreenCommand.CREATE_MULINO;
        });
        b_create_forno.addClickCommands(source -> {
            currentCommand = ScreenCommand.CREATE_FORNO;
        });
        b_create_macellaio.addClickCommands(source -> {
            currentCommand = ScreenCommand.CREATE_MACELLAIO;
        });
        b_create_deposito.addClickCommands(source -> {
            currentCommand = ScreenCommand.CREATE_DEPOSITO;
        });
        b_create_taglialegna.addClickCommands(source -> {
            currentCommand = ScreenCommand.CREATE_TAGLIALEGNA;
        });
        b_create_forestale.addClickCommands(source -> {
            currentCommand = ScreenCommand.CREATE_FORESTALE;
        });
        b_create_falegname.addClickCommands(source -> {
            currentCommand = ScreenCommand.CREATE_FALEGNAME;
        });
        b_create_pozzo.addClickCommands(source -> {
            currentCommand = ScreenCommand.CREATE_POZZO;
        });

        Button b2 = panel.addChild(new Button("Stop"));
        b2.addClickCommands(source -> {
            System.exit(0);
        });
    }

}
