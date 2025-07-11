/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.base.commands;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.mygame.base.walkers.Coltivatore;
import com.mygame.beni.TipoProdotto;
import com.mygame.buildings.edifici.coltivatore.EdificioColtivatore;
import com.mygame.models.ModelManager;

/**
 *
 * @author giovanni
 */
public class CommandMoveCreateProduct extends CommandMove {

    private final Vector3f location;
    private final Coltivatore owner;
    private TipoProdotto tipoProotto;
    private final EdificioColtivatore home;

    public CommandMoveCreateProduct(EdificioColtivatore myHome, Coltivatore owner, Vector3f destination, TipoProdotto tipoProdotto) {
        super(owner, destination);
        this.owner = owner;
        this.tipoProotto = tipoProdotto;
        this.home = myHome;
        this.location = destination;
    }

    @Override
    public void process() {
        Node n = ModelManager.getModelNodeForTipoProdotto(TipoProdotto.GRANO);
        //  Node n = Pool.getFactory().createItem(path);

        n.setLocalTranslation(location);
        owner.setResult(n);

        owner.add(n);
    }

}
