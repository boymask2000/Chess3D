/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.infrastructs;

import com.mygame.beni.TipoProdotto;
import com.mygame.buildings.edifici.ProductionBuilding;

/**
 *
 * @author giovanni
 */
public class Ordine {
    private int walkerOwner=0;

   

    private final ProductionBuilding edificio;
    private final TipoProdotto prodotto;

    public Ordine(ProductionBuilding edificio, TipoProdotto t) {
        this.edificio = edificio;
        this.prodotto = t;
    }

    public ProductionBuilding getEdificio() {
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
        return "Ordine{" + "walkerOwner=" + walkerOwner + ", edificio=" + edificio + ", prodotto=" + prodotto + '}';
    }


    
}
