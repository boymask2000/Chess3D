/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.buildings.edifici.coltivatore;

import com.jme3.math.Vector3f;
import com.mygame.buildings.TipoEdificio;

/**
 *
 * @author giovanni
 */
public class CampoColtivatoGrano extends CampoColtivato {
    
    public CampoColtivatoGrano(Vector3f position, int size) {
        super(TipoEdificio.CAMPO_GRANO, position, size);
    }


    
}
