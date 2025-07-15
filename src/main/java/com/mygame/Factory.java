/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;

/**
 *
 * @author giovanni
 */
public class Factory {

    private final SimpleApplication appl;

    public Factory(SimpleApplication appl) {
        this.appl = appl;

    }

    public Geometry myBox(String name, Vector3f loc, ColorRGBA color) {
        Box mesh = new Box(1, 1, 1);
        Geometry geom = new Geometry(name, mesh);
        Material mat = new Material(appl.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);
        geom.setMaterial(mat);
        geom.scale(0.1f);
        return geom;
    }

    public void createHome() {
        Box b = new Box(1, 2, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(appl.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Green);
        geom.setMaterial(mat);
        appl.getRootNode().attachChild(geom);

    }

    public Geometry createBox() {
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(appl.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
        appl.getRootNode().attachChild(geom);
        return geom;
    }



    public Node createItem(String modelPath) {
        if (modelPath == null) {
            return null;
        }
        Node n = (Node) Pool.getAssetManager().loadModel(modelPath);
        n.setLocalScale(0.2f);

        appl.getRootNode().attachChild(n);

        return n;

    }

    public Geometry createFromImage(String imgPath) {
        // Carica la texture dell'immagine
        Texture tex = Pool.getAssetManager().loadTexture("Textures/Wooden_log.png");

// Crea un materiale e applica la texture
        Material mat = new Material(Pool.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", tex);

// Crea un quad (rettangolo) con le dimensioni dell'immagine (es: 2x2 unità)
        Geometry quad = new Geometry("ImageQuad", new Quad(4, 4));
        quad.setMaterial(mat);
        return quad;
    }

}
