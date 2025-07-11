/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.buildings.edifici;

import com.mygame.base.walkers.Rimboscatore;
import com.jme3.math.Vector3f;
import com.mygame.buildings.TipoEdificio;

/**
 *
 * @author giovanni
 */
public class Forestale extends Edificio {



    private int timeToProduce = 10;

    public Forestale(Vector3f position) {
        super(TipoEdificio.FORESTALE, position);
        
        init();

    }


    private void init() {
        new Rimboscatore(this);

    }

}
