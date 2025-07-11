/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.base.commands;

import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.mygame.Pool;
import com.mygame.base.walkers.Walker0;
import com.mygame.beni.TipoProdotto;
import com.mygame.buildings.edifici.Edificio;
import com.mygame.models.ModelManager;

/**
 *
 * @author giovanni
 */
public class CommandMoveGet extends CommandMove {

    private Edificio edificio;
    private TipoProdotto prodotto;

    public CommandMoveGet(Walker0 owner, Vector3f destination, Edificio edificio, TipoProdotto prodotto) {
        super(owner, destination);
        this.edificio = edificio;
        this.prodotto = prodotto;

    }

    @Override
    public void process() {
        
        edificio.takeProduct(prodotto);

        owner.setProdottoTrasportato(prodotto);
    }
}
