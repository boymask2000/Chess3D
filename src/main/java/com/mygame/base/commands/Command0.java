/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.base.commands;

import com.mygame.base.walkers.Walker0;

/**
 *
 * @author giovanni
 */
public class Command0 {

    Walker0 owner;
    private int delayBefore = 0;

    private int delayAfter = 0;

    public Command0(Walker0 owner) {
        this.owner = owner;

    }

    public Walker0 getOwner() {
        return owner;
    }

    public void execute() {
        if (delayBefore > 0) {
            owner.setDelay(delayBefore);
        }

        process();

        if (delayAfter > 0) {
            owner.setDelay(delayAfter);
        }
    }

    public void process() {
    }

    public void setDelayBefore(int delayBefore) {
        this.delayBefore = delayBefore;
    }

    public void setDelayAfter(int delayAfter) {
        this.delayAfter = delayAfter;
    }
}
