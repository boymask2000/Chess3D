/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.catasto;

import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author giovanni
 */
public class InfoAlbero {

    private Spatial spatial;

    private List<Geometry> geometries;

    private Node node;


    private final Vector3f location;

    private static int PROG_ALBERO = 0;

    private final int idAlbero;

 
    public InfoAlbero(Node n, Vector3f location) {
        this.location = location;
        this.node = n;

        idAlbero = getProgr();

    }

    private static synchronized int getProgr() {
        PROG_ALBERO++;
        return PROG_ALBERO;
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

    boolean hasGeometry(Geometry geo) {
        for (Geometry g : geometries) {
            if (g.equals(geo)) {
                return true;
            }
        }
        return false;
    }


  
    public Vector3f getLocation() {
        return location;
    }

    public Node getNode() {
        return node;
    }
   public int getIdAlbero() {
        return idAlbero;
    }

  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InfoAlbero)) return false;
        InfoAlbero that = (InfoAlbero) o;
        return idAlbero == that.getIdAlbero();
    }

    @Override
    public int hashCode() {
        return Objects.hash(node, location);
    }

}
