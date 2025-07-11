/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.base.commands;

import com.jme3.math.Vector3f;
import com.mygame.base.walkers.Walker0;

/**
 *
 * @author giovanni
 */
public class CommandMove extends Command0{

    private Vector3f destination;

    public CommandMove(Walker0 owner, Vector3f destination) {
        super(owner);
        this.destination = destination;
    }

    public Vector3f getDestination() {
        return destination;
    }

    public void setDestination(Vector3f destination) {
        this.destination = destination;
    }


}
