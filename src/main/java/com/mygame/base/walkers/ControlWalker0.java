/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.base.walkers;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author giovanni
 */
public class ControlWalker0 extends AbstractControl {
    

    private Walker0 myData;

    public ControlWalker0(Walker0 data) {
        this.myData = data;
    }

    public Walker0 getMyData() {
        return myData;
    }

    @Override
    protected void controlUpdate(float tpf) {
        // logica di aggiornamento
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        // logica di rendering (opzionale)
    }
}