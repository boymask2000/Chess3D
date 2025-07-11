/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.base.commands;

import com.jme3.math.Vector3f;
import com.mygame.Pool;
import com.mygame.base.walkers.Walker0;
import com.mygame.beni.TipoProdotto;
import com.mygame.catasto.InfoAlbero;

/**
 *
 * @author giovanni
 */
public class CommandMoveTagliaAlbero extends CommandMove {

    private final InfoAlbero albero;
    private final Vector3f location;


    public CommandMoveTagliaAlbero(Walker0 owner, Vector3f destination, InfoAlbero albero) {
        super(owner, destination);
        this.location = destination;
        this.albero = albero;
    
    }

    @Override
    public void process() {
     
        Pool.getRootNode().detachChild(albero.getNode());
        owner.setProdottoTrasportato(TipoProdotto.TRONCO);


    }

}
