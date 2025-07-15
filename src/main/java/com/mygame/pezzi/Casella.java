/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.pezzi;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.mygame.Pool;
import com.mygame.models.ModelManager;

/**
 *
 * @author giovanni
 */
public class Casella {

    public static final String BIANCO = "B";
    public static final String NERO = "N";

    private static final String colonne = "ABCDEFGH";
    private static final int SIZE = 10;

    private final Vector3f position;
    private final Node node;

    public Casella(
            int ncol, int nrow) {

        int col = ncol * SIZE;
        int row = nrow * SIZE;
        //  System.out.println(tipo+" "+col+" "+row);
        this.position = new Vector3f(col, 0, row);

        // Pool.addPezzo(this);
        System.out.println(position);
        String colore = NERO;
        if ((ncol + nrow) % 2 == 0) {
            colore = BIANCO;
        }
        node = ModelManager.getModelNodeForCasella(colore);
        node.setLocalTranslation(position);
    }

    public Vector3f getPosition() {
        return position;
    }

    public Node getNode() {
        return node;
    }
}
