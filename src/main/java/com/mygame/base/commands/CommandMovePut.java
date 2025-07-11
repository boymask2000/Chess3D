/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.base.commands;

import com.jme3.math.Vector3f;
import com.mygame.base.walkers.Walker0;
import com.mygame.beni.TipoProdotto;
import com.mygame.buildings.edifici.Edificio;
import com.mygame.buildings.edifici.ProductionBuilding;

/**
 *
 * @author giovanni
 */
public class CommandMovePut extends CommandMove {
    
    private Edificio edificio;
    private TipoProdotto prodotto;
    
    public CommandMovePut(Walker0 owner, Vector3f destination, Edificio edificio, TipoProdotto prodotto) {
        super(owner, destination);
        this.edificio = edificio;
        this.prodotto = prodotto;
    }
    
    @Override
    public void process() {
        if( edificio instanceof ProductionBuilding){
            ProductionBuilding ee = (ProductionBuilding)edificio;
            ee.incGiacenza();
        }
 
        edificio.storeItem(prodotto);

        owner.removeMerceTrasportata();
            
    }
    
}
