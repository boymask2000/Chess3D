/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.beni;

/**
 *
 * @author giovanni
 */
public enum TipoProdotto {
    MAIALE(null),  //Allevatore->Maiale->macellaio->carne
    CARNE(null),
    PANE(null), // Coltivatore->grano->farina->pane
    GRANO(null),
    FARINA(null),
    ACQUA(null),
    ASSE_LEGNO(null), //Taglialegna->tronco->falegname->asse_legno
    TRONCO(null),
    ALBERO("Models/arbol.j3o");

    private String modelPath;

    TipoProdotto(String pathModel) {
        modelPath = pathModel;
    }

    public String getModelPath() {
        return modelPath;
    }
}
