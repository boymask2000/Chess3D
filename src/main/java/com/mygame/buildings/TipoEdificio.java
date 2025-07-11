/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.buildings;

import com.mygame.beni.TipoProdotto;

/**
 *
 * @author giovanni
 */
public enum TipoEdificio {
    CAMPO_GRANO(TipoProdotto.GRANO),
    //DEPOSITO(null, "Models/warehouse.j3o"),
    DEPOSITO(null),
    TAGLIALEGNA(TipoProdotto.TRONCO),
    FORESTALE(TipoProdotto.ALBERO),
    FALEGNAME(TipoProdotto.ASSE_LEGNO),
    MACELLAIO(TipoProdotto.CARNE),
    FORNO(TipoProdotto.PANE),
    MULINO(TipoProdotto.FARINA),
    ALLEVATORE_MAIALI(TipoProdotto.MAIALE),
    COLTIVATORE_GRANO(TipoProdotto.GRANO),
    POZZO(TipoProdotto.ACQUA);

    TipoEdificio(TipoProdotto t) {
        prodotto = t;

    }

    private final TipoProdotto prodotto;

    public TipoProdotto getProdotto() {
        return prodotto;
    }
}
