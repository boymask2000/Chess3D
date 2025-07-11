/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.buildings.edifici;

import com.jme3.math.Vector3f;
import com.mygame.beni.TipoProdotto;
import com.mygame.buildings.TipoEdificio;

/**
 *
 * @author giovanni
 */
public class Pozzo extends ProductionBuilding{
    
    public Pozzo( Vector3f position) {
        super(TipoEdificio.POZZO, position);
        tipoProdotto = TipoProdotto.ACQUA;
    }
    
}
