/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.base.walkers;

import com.mygame.base.commands.CommandMove;
import com.mygame.base.commands.CommandMoveCreateAlbero;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.mygame.beni.TipoProdotto;
import com.mygame.buildings.edifici.Edificio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author giovanni
 */
public class Rimboscatore extends Walker0 {

    private final Edificio home;
    private long timeToCreateTree;
    private long timeToRest;

    private enum Stato {
        RETURNING_HOME, IDLE, GOING_TO_POSITION
    };
    private Stato currentStato = Stato.IDLE;

    private TipoProdotto tipoProdRichiesto;

    private final List<Node> myTrees = new ArrayList<>();

    public Rimboscatore(Edificio ed) {
        super(ed);
        this.home = ed;

    }

    @Override
    public void processStanding() {

        if (countTrees() >= 10) {
            return;
        }
        Vector3f pos = getDestination();
        CommandMoveCreateAlbero c1 = new CommandMoveCreateAlbero(this, pos);
        c1.setDelayBefore(5);
        addCommand(c1);
        CommandMove move = new CommandMove(this, home.getPosition());
        move.setDelayAfter(5);
        addCommand(move);
    }

//    @Override
//    public void update(float tpf) {
//        long currTime = (new Date()).getTime();
//        if (currTime - timeToCreateTree < 5000) {
//            return;
//        }
//        if (currTime - timeToRest < 5000) {
//            return;
//        }
//        super.update(tpf);
//    }
    private Vector3f getDestination() {
        Vector3f pos = home.getPosition();

        float radius = 15f;

        float offsetX = (FastMath.nextRandomFloat() * 2 - 1) * radius;
        float offsetZ = (FastMath.nextRandomFloat() * 2 - 1) * radius;

        return pos.add(offsetX, 0, offsetZ);
    }

    @Override
    public void setResult(Object n) {
        timeToCreateTree = (new Date()).getTime();

        if (n instanceof Node) {
            myTrees.add((Node) n);
        }

    }

    private synchronized int countTrees() {
        List<Node> toRemove = new ArrayList();

        timeToRest = (new Date()).getTime();
        int count = 0;
        for (Node n : myTrees) {
            if (n.getParent() != null) {
                count++;
            } else {
                toRemove.add(n);

            }
        }
        myTrees.removeAll(toRemove);
        return count;
    }
}
