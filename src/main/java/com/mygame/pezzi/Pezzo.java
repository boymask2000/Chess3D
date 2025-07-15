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
public class Pezzo {

    private static final String colonne = "ABCDEFGH";
    private static final int SIZE = 10;

    private final TipoPezzo tipoPezzo;
    private final Vector3f position;
    private final Node node;


    public Pezzo(TipoPezzo tipo, String scol, String srow) //Pedone A4
    {
        int col = (colonne.indexOf(scol) + 1) * SIZE;
        int row = Integer.parseInt(srow) * SIZE;
      //  System.out.println(tipo+" "+col+" "+row);
        this.position = new Vector3f(col, 0, row);
        this.tipoPezzo = tipo;
        Pool.addPezzo(this);

        System.out.println(position);

        node = ModelManager.getModelNodeForTipoPezzo(tipoPezzo);
        node.setLocalTranslation(position);
    }

    public TipoPezzo getTipoPezzo() {
        return tipoPezzo;
    }

    public Vector3f getPosition() {
        return position;
    }
    
    
    public Node getNode() {
        return node;
    }
}
