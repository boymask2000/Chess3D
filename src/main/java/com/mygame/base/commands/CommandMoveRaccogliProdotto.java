/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.base.commands;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.mygame.Pool;
import com.mygame.base.walkers.Walker0;
import com.mygame.beni.TipoProdotto;
import com.mygame.catasto.InfoAlbero;

/**
 *
 * @author giovanni
 */
public class CommandMoveRaccogliProdotto extends CommandMove {

    private final Node node;
    private final Vector3f location;
    private final TipoProdotto tipoProdotto;


    public CommandMoveRaccogliProdotto(Walker0 owner, Vector3f destination, Node node, TipoProdotto t) {
        super(owner, destination);
        this.location = destination;
        this.node = node;
        this.tipoProdotto=t;
    
    }

    @Override
    public void process() {
     
        Pool.getRootNode().detachChild(node);
        owner.setProdottoTrasportato(tipoProdotto);


    }

}
