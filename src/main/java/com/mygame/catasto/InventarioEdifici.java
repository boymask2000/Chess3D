/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.catasto;

import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.mygame.buildings.TipoEdificio;
import com.mygame.buildings.edifici.Edificio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giovanni
 */
public class InventarioEdifici {

    private static List<InfoEdificio> infos = new ArrayList<>();

    public static void add(Edificio edificio, Spatial model) {
        infos.add(new InfoEdificio(edificio, model));

    }

    public static Edificio searchEdificioByGeometry(Geometry geo) {
        for (InfoEdificio info : infos) {
            if (info.hasGeometry(geo)) {
                return info.getEdificio();
            }
        }
        return null;
    }

    public static Edificio searchEdificioByType(TipoEdificio tipo, Vector3f requester) {
        for (InfoEdificio info : infos) {
            if (info.getEdificio().getTipoEdificio() == tipo) {
                return info.getEdificio();
            }
        }
        return null;

    }
    public static Edificio searchDeposito(Vector3f requester){
        return searchEdificioByType(TipoEdificio.DEPOSITO, requester);
        
    }

}
