/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

/**
 *
 * @author giovanni
 */
public class DefaultAppState extends BaseAppState {

    private final Main main;

    public DefaultAppState(Main main) {
        this.main = main;
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
    }

    @Override
    protected void initialize(Application aplctn) {

       Pool.setTerrain(new Terrain((SimpleApplication) aplctn));
      //  attachCenterMark();
        //    Pool.getFactory().createBox();

    }

    private void attachCenterMark() {
        Geometry c = Pool.getFactory().myBox("center mark",
                Vector3f.ZERO, ColorRGBA.White);
        c.scale(4);
        c.setLocalTranslation(Pool.getSettings().getWidth() / 2,
                Pool.getSettings().getHeight() / 2, 0);
        Pool.getGuiNode().attachChild(c); // attach to 2D user interface
    }

    @Override
    protected void cleanup(Application aplctn) {
        //    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void onEnable() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void onDisable() {
        //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
