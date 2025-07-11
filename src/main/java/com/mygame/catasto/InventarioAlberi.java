/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.catasto;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giovanni
 */
public class InventarioAlberi {

    private static List<InfoAlbero> infos = new ArrayList<>();

    public static void add(Node n, Vector3f location) {
        InfoAlbero info = new InfoAlbero(n, location);

        infos.add(info);

    }

    public synchronized static InfoAlbero getAlbero(Vector3f position) {
        if (infos.size() == 0) {
            return null;
        }
        InfoAlbero minAlbero = infos.get(0);
        float minDistanza = position.distance(minAlbero.getLocation());

        for (InfoAlbero ii : infos) {

            float distanza = position.distance(ii.getLocation());
            if (distanza < minDistanza) {
                minDistanza = distanza;
                minAlbero = ii;
            }
          //  return ii;

        }
        return minAlbero;
    }

    public synchronized static void remove(InfoAlbero albero) {

        boolean ok = infos.remove(albero);

        List<InfoAlbero> temp = new ArrayList<>();

        for (InfoAlbero i : infos) {
            if (i.getNode().getParent() != null) {
                temp.add(i);
            }
        }
        infos = temp;

    }

}
