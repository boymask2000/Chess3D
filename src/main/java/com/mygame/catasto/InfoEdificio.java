/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.catasto;

import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import com.mygame.buildings.TipoEdificio;
import com.mygame.buildings.edifici.Edificio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giovanni
 */
public class InfoEdificio {

    private Spatial spatial;

    private List<Geometry> geometries;
    private final Edificio edificio;
    private Node node;

    public InfoEdificio(Edificio edificio, Spatial spatial) {
        this.edificio = edificio;

        geometries = getAllGeometries(spatial);
        
        edificio.setGeometries(geometries);

    }

    public static List<Geometry> getAllGeometries(Spatial spatial) {
        List<Geometry> geometries = new ArrayList<>();

        spatial.depthFirstTraversal(new SceneGraphVisitor() {
            @Override
            public void visit(Spatial s) {
                if (s instanceof Geometry) {
                    geometries.add((Geometry) s);
                }
            }
        });

        return geometries;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    boolean hasGeometry(Geometry geo) {
        for (Geometry g : geometries) {
            if (g.equals(geo)) {
                return true;
            }
        }
        return false;
    }

    private void setGeometries(List<Geometry> geometries) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
