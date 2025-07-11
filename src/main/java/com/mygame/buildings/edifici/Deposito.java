/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.buildings.edifici;

import com.jme3.math.Vector3f;
import com.mygame.Pool;
import com.mygame.beni.TipoProdotto;
import com.mygame.buildings.TipoEdificio;
import com.mygame.infrastructs.GiacenzaInvGlobale;
import com.mygame.infrastructs.InventarioGlobale;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import java.util.Map;

/**
 *
 * @author giovanni
 */
public class Deposito extends Edificio {

    public Deposito(Vector3f position) {
        super(TipoEdificio.DEPOSITO, position);

        Pool.getFactory().createWalker(this);
    }

    @Override
    protected Container buildMenu() {
        Map<TipoProdotto, Integer> map = giacenze.getGiacenze();
        Container menu = super.buildMenu(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        menu.addChild(new Label("--------Materie prime---------"));
        TipoProdotto[] tipi = TipoProdotto.values();
        for (TipoProdotto pp : tipi) {
            Integer v = map.get(pp);
            if (v == null) {
                v = 0;
            }
            menu.addChild(new Label(pp.toString() + ": " + v));
        }

        return menu;
    }

    @Override
    public void storeItem(TipoProdotto t) {
        super.storeItem(t);
        InventarioGlobale.add(new GiacenzaInvGlobale(this, t));
    }
}
