/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.base.walkers;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.mygame.base.commands.CommandMove;
import com.mygame.base.commands.CommandMoveCreateProduct;
import com.mygame.base.commands.CommandMovePut;
import com.mygame.base.commands.CommandMoveRaccogliProdotto;
import com.mygame.beni.TipoProdotto;
import com.mygame.buildings.edifici.coltivatore.EdificioColtivatore;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giovanni
 */
public class Coltivatore extends Walker0 {

    private enum Stato {
        SEMINANDO, ATTESA, RACCOGLIENDO
    };
    private Stato currentStato = Stato.SEMINANDO;

    private final Vector3f centerArea;
    private final TipoProdotto tipoProdotto;

    private List<Vector3f> positions = new ArrayList<>();

    private final List<Node> myPlants = new ArrayList<>();
    private final EdificioColtivatore myHome;

    public Coltivatore(EdificioColtivatore home, Vector3f centerArea, TipoProdotto tipoProdotto) {
        super(home);
        this.centerArea = centerArea;
        this.tipoProdotto = tipoProdotto;
        this.myHome = home;
        speed = 0.05f;
        setDelay(1);
        initPositions();
    }

    @Override
    public void processStanding() {
        switch (currentStato) {
            case ATTESA:
                counter = 0;
                currentStato = Stato.RACCOGLIENDO;
                break;
            case RACCOGLIENDO:
                boolean racc = processRaccogliendo();
                if (!racc) {
                    currentStato = Stato.SEMINANDO;
                    myPlants.clear();
                }
                break;
            case SEMINANDO:
                boolean going = processSeminando();
                if (!going) {
                    currentStato = Stato.ATTESA;
                }
                break;
        }

    }

    public boolean processRaccogliendo() {
        System.out.println("counter = " + counter);
        if (counter >= myPlants.size()) {
            return false;
        }
        Node plantNode = myPlants.get(counter);
        Vector3f pos = getDestination();
        CommandMoveRaccogliProdotto c1 = new CommandMoveRaccogliProdotto(this, pos, plantNode, tipoProdotto);
        c1.setDelayBefore(5);
        addCommand(c1);
        CommandMovePut move = new CommandMovePut(this, home.getPosition(), home, tipoProdotto);
        move.setDelayAfter(5);
        addCommand(move);
        return true;
    }

    public boolean processSeminando() {
        countPlants();
        if (counter >= positions.size()) {
            counter = 0;
            return false;
        }
        Vector3f pos = getDestination();
        CommandMoveCreateProduct c1 = new CommandMoveCreateProduct(myHome, this, pos, tipoProdotto);
        c1.setDelayBefore(5);
        addCommand(c1);
        CommandMove move = new CommandMove(this, home.getPosition());
        move.setDelayAfter(5);
        addCommand(move);
        return true;
    }

    int counter = 0;

    private Vector3f getDestination() {

        return positions.get(counter++);
    }

    private synchronized int countPlants() {
        List<Node> toRemove = new ArrayList();

        //   timeToRest = (new Date()).getTime();
        int count = 0;
        for (Node n : myPlants) {
            if (n.getParent() != null) {
                count++;
            } else {
                toRemove.add(n);

            }
        }
        myPlants.removeAll(toRemove);
        return count;
    }

    public void add(Node n) {
        myPlants.add(n);

    }

    private void initPositions() {
        float gap = (float) 2;
        int size = 1;
        float startx = centerArea.x;
        float startz = centerArea.z;

        for (float x = startx - size * gap; x < startx + size * gap; x += gap) {
            for (float z = startz - size * gap; z < startz + size * gap; z += gap) {
                positions.add(new Vector3f(x, 0, z));
            }
        }

    }
}
