/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.models;

import com.jme3.scene.Node;
import com.mygame.Pool;
import com.mygame.pezzi.Casella;
import com.mygame.pezzi.TipoPezzo;

/**
 *
 * @author giovanni
 */
public class ModelManager {

    private static InfoModello getModelInfoPerTipoPezzo(TipoPezzo t) {
        float size = 4f;
        switch (t) {
            case ALFIERE_B:
                return new InfoModello("Models/alfiere_b.j3o", size);
            case ALFIERE_N:
                return new InfoModello("Models/alfiere_n.j3o", size);
            case CAVALLO_B:
                return new InfoModello("Models/cavallo_b.j3o", size);
            case CAVALLO_N:
                return new InfoModello("Models/cavallo_n.j3o", size);
            case KING_B:
                return new InfoModello("Models/king_b.j3o", size);
            case KING_N:
                return new InfoModello("Models/king_n.j3o", size);
            case PEDONE_B:
                return new InfoModello("Models/pedone_b.j3o", size);
            case PEDONE_N:
                return new InfoModello("Models/pedone_n.j3o", size);
            case QUEEN_B:
                return new InfoModello("Models/regina_b.j3o", size);
            case QUEEN_N:
                return new InfoModello("Models/regina_n.j3o", size);
            case TORRE_B:
                return new InfoModello("Models/torre_b.j3o", size);
            case TORRE_N:
                return new InfoModello("Models/torre_n.j3o", size);
        }
        return null;
    }

    public static Node getModelNodeForCasella(String color) {
        InfoModello info = getModelInfoPerCasella(color);
         if (info == null) {
            return null;
        }
        return activate(info, info.getLocalScale());
    }

    public static Node getModelNodeForTipoPezzo(TipoPezzo t) {
        InfoModello info = getModelInfoPerTipoPezzo(t);
        if (info == null) {
            return null;
        }
        return activate(info, info.getLocalScale());
    }

    private static Node activate(InfoModello info, float scale) {
        Node n = (Node) Pool.getAssetManager().loadModel(info.getPath());
        n.setLocalScale(info.getLocalScale() * scale);

        Pool.getRootNode().attachChild(n);

        return n;
    }

    private static InfoModello getModelInfoPerCasella(String color) {
        switch (color) {
            case Casella.BIANCO:
                return new InfoModello("Models/casella_b.j3o", 2f);

            case Casella.NERO:
                return new InfoModello("Models/casella_blu.j3o", 2f);
        }
        return null;
    }

}
