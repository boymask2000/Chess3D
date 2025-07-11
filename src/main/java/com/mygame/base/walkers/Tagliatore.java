/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.base.walkers;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.mygame.base.commands.CommandMovePutTronco;
import com.mygame.base.commands.CommandMoveTagliaAlbero;
import com.mygame.beni.TipoProdotto;
import com.mygame.buildings.edifici.ProduttoreRaccoglitoreBuilding;
import com.mygame.catasto.InfoAlbero;
import com.mygame.catasto.InventarioAlberi;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author giovanni
 */
public class Tagliatore extends Walker0 {

    private final ProduttoreRaccoglitoreBuilding home;
    private long timeToCreateTree;
    private long timeToRest;

    private enum Stato {
        RETURNING_HOME, IDLE, GOING_TO_POSITION
    };
    private Stato currentStato = Stato.IDLE;

    private TipoProdotto tipoProdRichiesto;

    private List<Node> myTrees = new ArrayList<>();

    public Tagliatore(ProduttoreRaccoglitoreBuilding ed) {
        super(ed);
        this.home = ed;

    }

    @Override
    public void processStanding() {
        if( !home.canProduce())return;

        InfoAlbero albero = getDestination();
        if (albero == null) {
            return;
        }
        Vector3f pos = albero.getLocation();

        CommandMoveTagliaAlbero move = new CommandMoveTagliaAlbero(this, pos, albero);
        move.setDelayAfter(5);
        addCommand(move);

        CommandMovePutTronco move2 = new CommandMovePutTronco(this, home.getPosition(), home);
        move2.setDelayAfter(5);
        addCommand(move2);
    }

    private InfoAlbero getDestination() {
        InfoAlbero albero = InventarioAlberi.getAlbero(home.getPosition());
        if (albero == null) {
            return null;
        }
        InventarioAlberi.remove(albero);
        return albero;
    }

    @Override
    public void setResult(Object n) {
        timeToCreateTree = (new Date()).getTime();

        if (n instanceof Node) {
            myTrees.add((Node) n);
        }

    }

}
