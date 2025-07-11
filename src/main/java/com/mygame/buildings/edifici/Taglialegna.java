/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.buildings.edifici;

import com.jme3.anim.AnimComposer;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.mygame.Pool;
import com.mygame.base.walkers.ControlWalker0;
import com.mygame.base.walkers.Tagliatore;
import com.mygame.buildings.TipoEdificio;

/**
 *
 * @author giovanni
 */
public class Taglialegna extends ProduttoreRaccoglitoreBuilding {

    long timeToProduce = 10;

    public Taglialegna(Vector3f position) {
        super(TipoEdificio.TAGLIALEGNA, position);
        
        init();
    }

    private void init() {
        new Tagliatore(this);
//        AnimComposer control;
//        Node walkerNode = (Node) Pool.getAssetManager().loadModel("Models/Oto/Oto.mesh.xml");
//        walkerNode.setLocalScale(0.2f);
//        control = walkerNode.getControl(AnimComposer.class);
//        control.setCurrentAction("Walk");
//
//        Pool.getRootNode().attachChild(walkerNode);
//        ControlWalker0 controlWalk0 = new ControlWalker0(new Tagliatore(this));
//        walkerNode.addControl(controlWalk0);
    }
}
