/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.buildings;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;
import com.mygame.buildings.edifici.Edificio;

/**
 *
 * @author giovanni
 * Serve ad associare l'edificio all'oggetto grafico
 */
public class ControlEdificio extends AbstractControl {
    

    private Edificio myData;

    public ControlEdificio(Edificio data) {
        this.myData = data;
    }

    public Edificio getMyData() {
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

