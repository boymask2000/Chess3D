/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.buildings.edifici;

import com.mygame.infrastructs.Ordine;
import com.mygame.infrastructs.GiacenzaInvGlobale;
import com.mygame.infrastructs.ManagerOrdini;
import com.mygame.infrastructs.InventarioGlobale;
import com.jme3.math.Vector3f;
import com.mygame.beni.TipoProdotto;
import com.mygame.buildings.TipoEdificio;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giovanni
 */
public class ProductionBuilding extends Edificio {

    private enum Stato {
        WORKING, IDLE
    };
    private Stato currentStato = Stato.WORKING;

    private boolean working = true;

    protected TipoProdotto tipoProdotto;
    protected int maxGiacenza = 5;

    private int giacenza = 0;
    protected int timeToProduce = 10; // Numero di secondi per produrre
    private List<TipoProdotto> materiePrimePerProdurre; //Necessarie per produrre
    private final List<TipoProdotto> materiePrimeOrdinate = new ArrayList<>();

    public ProductionBuilding(TipoEdificio tipo, Vector3f position) {
        super(tipo, position);
        tipoProdotto = tipo.getProdotto();
        init();
    }

    private void init() {
        materiePrimePerProdurre = com.mygame.beni.MateriePrime.getMateriePrime(tipoProdotto);
        currentStato = Stato.IDLE;
        Date now = new Date();
        startWait = now.getTime();
    }

    private long startWait = 0;

    @Override
    public void update(float tpf) {
        switch (currentStato) {
            case IDLE:
                Date dat = new Date();
                if (dat.getTime() - startWait >= timeToProduce * 1000) {
                    currentStato = Stato.WORKING;
                }
                break;
            case WORKING:
                if (canProduce()) {

                    if (verificaEdAggiornaMateriePrime()) {

                        produci();
                        currentStato = Stato.IDLE;
                        Date now = new Date();
                        startWait = now.getTime();
                    }

                }
                break;
        }

    }

    protected void produci() {
   //     System.out.println(toString() + " Produce");

        incGiacenza();
       // System.out.println(toString() + " prodotto. Giacenza = " + giacenza);
        InventarioGlobale.add(new GiacenzaInvGlobale(this, tipoProdotto));
    }

    public boolean canProduce() {
        return working == true && giacenza < maxGiacenza;
    }

    public void incGiacenza() {
        giacenza++;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    private boolean verificaEdAggiornaMateriePrime() {
        List<TipoProdotto> missings = giacenze.checkGiacenze(materiePrimePerProdurre);
    //    System.out.println(this + " missings = " + missings.size());
        if (missings.size() > 0) {
            for (TipoProdotto miss : missings) {
                if (!materiePrimeOrdinate.contains(miss)) {
                   
                    creaOrdine(miss);
                }
            }
            return false;
        }

        // I prodotti necessari ci sono. Li tolgo dalla disponibilità e do OK per produrre
        for (TipoProdotto t : materiePrimePerProdurre) {
    //        System.out.println("rimuovo " + t);
            giacenze.remove(t);

        }
        return true;

    }

    private void creaOrdine(TipoProdotto t) {
        Ordine ordine = new Ordine(this, t);
        ManagerOrdini.add(ordine);
        materiePrimeOrdinate.add(t);
  //      System.out.println("Creato ordine per " + t);
    }

    public int getGiacenza() {
        return giacenza;
    }

    private Map<TipoProdotto, Integer> buildGiacenza() {

        return giacenze.getGiacenze();
    }

    @Override
    protected Container buildMenu() {
        Map<TipoProdotto, Integer> map = buildGiacenza();
        Container menu = super.buildMenu(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        menu.addChild(new Label("-----Produzione----------------"));

        menu.addChild(new Label(getTipoEdificio().getProdotto().toString() + ": " + getGiacenza()));
        menu.addChild(new Label("--------Materie prime---------"));
        for (TipoProdotto mat : materiePrimePerProdurre) {
            Integer v = map.get(mat);
            if (v == null) {
                v = 0;
            }
            menu.addChild(new Label(mat.toString() + ": " + v));
        }
//        for (TipoProdotto t : map.keySet()) {
//            menu.addChild(new Label(t.toString() + ": " + map.get(t)));
//        }
        return menu;
    }

    @Override
    public void storeItem(TipoProdotto t) {
//System.out.println("Stored " + t);
        giacenze.add(t);
//        for( TipoProdotto r: materiePrimeOrdinate){
//            System.out.println("in Ordinato "+r);
//        }
        boolean d = materiePrimeOrdinate.remove(t);
  //      System.out.println("removed " + d);

    }
}
