/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.buildings.edifici.coltivatore;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.mygame.Pool;
import com.mygame.buildings.TipoEdificio;
import com.mygame.models.ModelManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author giovanni
 */
public class CampoColtivato extends com.mygame.buildings.edifici.Edificio {

    private final List<Item> plants = new ArrayList<>();

    private long dataInizioSemina;
    private final long tempoInizioCrescita = 2000; // 10 secondi

    private enum Stato {
        WAITING, WAITING_INIZIO_CRESCITA, INIZIO_CRESCITA
    };
    private Stato currentStato = Stato.WAITING;

    private final int size;

    public CampoColtivato(TipoEdificio tipo, Vector3f position, int size) {
        super(tipo, position);
        this.size = size;
    }

    @Override
    public void update(float tpf) {
        System.out.println("CampoColtivato update " + currentStato);
        switch (currentStato) {
            case WAITING:
                break;
            case WAITING_INIZIO_CRESCITA:
                processWaitingInizioCrescita();
                break;
            case INIZIO_CRESCITA:
                break;
        }

    }


    int counterCrescita = 10;

    private void processWaitingInizioCrescita() {
        long now = getTime();
        if (now - dataInizioSemina < tempoInizioCrescita) {            
            return;
        }
          System.out.println("ok");
        dataInizioSemina = getTime();
        currentStato = Stato.WAITING_INIZIO_CRESCITA;

        counterCrescita--;
          System.out.println("counterCrescita = "+counterCrescita);
        if (counterCrescita <= 0) {
            currentStato = Stato.INIZIO_CRESCITA;
        }

        if (counterCrescita == 0) {
            return;
        }
        creaPiante();
    }

    private long getTime() {
        Date d = new Date();
        return d.getTime();
    }

    private void creaPiante() {
        float scale = 0.1f * (11-counterCrescita);
        float gap = (float) 2;

        cleanPlants();

        float startx = getPosition().x;
        float startz = getPosition().z;

        for (float x = startx - size * gap; x < startx + size * gap; x += gap) {
            for (float z = startz - size * gap; z < startz + size * gap; z += gap) {
                Node n = ModelManager.getModelNodeForTipoProdottoScaled(getTipoEdificio().getProdotto(), scale);
                n.setLocalTranslation(x, 0, z);
                System.out.println("Creato   " + n+ " counter = "+counterCrescita+ "  scale="+scale);
                plants.add(new Item(new Vector3f(x, 0, z), n));
            }
        }

    }

    private void cleanPlants() {
        for (Item item : plants) {
            Pool.getRootNode().detachChild(item.node);
        }
        plants.clear();
    }

    public void startSemina() {
        inizioSemina();
        currentStato = Stato.WAITING_INIZIO_CRESCITA;
    }
    public void inizioSemina() {
        dataInizioSemina = getTime();
        currentStato = Stato.WAITING_INIZIO_CRESCITA;
    }
    class Item {

        Vector3f pos;
        Node node;

        private Item(Vector3f v, Node n) {
            this.pos = v;
            this.node = n;
        }
    };
}
