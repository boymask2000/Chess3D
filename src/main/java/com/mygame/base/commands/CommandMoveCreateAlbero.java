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
import com.mygame.catasto.InventarioAlberi;
import com.mygame.models.ModelManager;

/**
 *
 * @author giovanni
 */
public class CommandMoveCreateAlbero extends CommandMove {

 
    private final Vector3f location;
    private final Walker0 owner;

    public CommandMoveCreateAlbero(Walker0 owner, Vector3f destination) {
        super(owner, destination);
        this.location = destination;
      
        this.owner = owner;
    }

    @Override
    public void process() {
       Node n =ModelManager.getModelNodeForTipoProdotto(TipoProdotto.ALBERO);
      //  Node n = Pool.getFactory().createItem(path);
        n.setLocalTranslation(location);
        owner.setResult(n);
        
        InventarioAlberi.add(n, location);
    }

}
