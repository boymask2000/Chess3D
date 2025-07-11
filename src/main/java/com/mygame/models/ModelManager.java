/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.models;

import com.jme3.scene.Node;
import com.mygame.Pool;
import com.mygame.beni.TipoProdotto;
import static com.mygame.beni.TipoProdotto.ACQUA;
import static com.mygame.beni.TipoProdotto.ALBERO;
import static com.mygame.beni.TipoProdotto.ASSE_LEGNO;
import static com.mygame.beni.TipoProdotto.CARNE;
import static com.mygame.beni.TipoProdotto.FARINA;
import static com.mygame.beni.TipoProdotto.GRANO;
import static com.mygame.beni.TipoProdotto.MAIALE;
import static com.mygame.beni.TipoProdotto.PANE;
import static com.mygame.beni.TipoProdotto.TRONCO;
import com.mygame.buildings.TipoEdificio;
import static com.mygame.buildings.TipoEdificio.POZZO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giovanni
 */
public class ModelManager {

    private final static List<InfoModelloAlbero> modelForAlbero = new ArrayList<>();
    private final static List<InfoModello> modelForEdifici = new ArrayList<>();

    static {
        modelForAlbero.add(new InfoModelloAlbero("Models/arbol.j3o", 1.2f));
        modelForAlbero.add(new InfoModelloAlbero("Models/tree.j3o", 0.2f));

    }

    private static InfoModello getModelInfoPerTipoProdotto(TipoProdotto t) {
        switch (t) {
            case ALBERO:
                return getRandomModPathForAlbero();
            case TRONCO:
                return new InfoModello("Models/tronco.j3o", 0.5f);
            case ACQUA:
                return new InfoModello("Models/bucket.j3o", 1.5f);
            case GRANO:
                return new InfoModello("Models/wipalm.j3o", 0.3f);
            case ASSE_LEGNO:
            case CARNE:
            case FARINA:

            case MAIALE:
            case PANE:

                return null;

        }
        return null;
    }

    private static InfoModello getModelInfoPerTipoEdificio(TipoEdificio t) {
        switch (t) {
            case DEPOSITO:
                return new InfoModello("Models/cottage.j3o", 0.3f);
            case POZZO:
                return new InfoModello("Models/well.j3o", 0.1f);
            case ALLEVATORE_MAIALI:
            case COLTIVATORE_GRANO:
            case FALEGNAME:
            case FORESTALE:
            case FORNO:
            case MACELLAIO:
            case MULINO:
            case TAGLIALEGNA:
                return new InfoModello("Models/home1.j3o", 0.1f);

        }
        return null;
    }

    public static Node getModelNodeForTipoEdificio(TipoEdificio t) {
        InfoModello info = getModelInfoPerTipoEdificio(t);
        if (info == null) {
            return null;
        }
        return activate(info, 1f);
    }

    public static Node getModelNodeForTipoProdotto(TipoProdotto t) {

        InfoModello info = getModelInfoPerTipoProdotto(t);

        if (info == null) {
            return null;
        }

        return activate(info, 1f);
    }

    public static Node getModelNodeForTipoProdottoScaled(TipoProdotto t, float scale) {

        InfoModello info = getModelInfoPerTipoProdotto(t);

        if (info == null) {
            return null;
        }

        return activate(info, scale);
    }

    private static Node activate(InfoModello info, float scale) {
        Node n = (Node) Pool.getAssetManager().loadModel(info.getPath());
        n.setLocalScale(info.getLocalScale() * scale);

        Pool.getRootNode().attachChild(n);

        return n;
    }

    private static InfoModello getRandomModPathForAlbero() {
        int r = (int) (Math.random() * modelForAlbero.size()); // 0 to 100

        return modelForAlbero.get(r);
    }

}
