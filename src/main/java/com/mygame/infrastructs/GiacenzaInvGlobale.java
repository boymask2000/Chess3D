/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.infrastructs;

import com.mygame.beni.TipoProdotto;
import com.mygame.buildings.edifici.Edificio;
import java.util.Date;

/**
 *
 * @author giovanni
 */
public class GiacenzaInvGlobale {

    private int walkerOwner = 0;

    private final Edificio edificio;

    private final TipoProdotto prodotto;

    private long dataCreazione = 0;

    public GiacenzaInvGlobale(Edificio ed, TipoProdotto tipoProdotto) {
        this.edificio = ed;
        this.prodotto = tipoProdotto;

        Date d = new Date();
        dataCreazione = d.getTime();
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public TipoProdotto getProdotto() {
        return prodotto;
    }

    public int getWalkerOwner() {
        return walkerOwner;
    }

    public void setWalkerOwner(int walkerOwner) {
        this.walkerOwner = walkerOwner;
    }

    @Override
    public String toString() {
        return "Giacenza{" + "edificio=" + edificio + ", prodotto=" + prodotto + '}';
    }

    public long getDataCreazione() {
        return dataCreazione;
    }

}
