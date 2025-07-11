/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.beni;

import static com.mygame.beni.TipoProdotto.ACQUA;
import static com.mygame.beni.TipoProdotto.GRANO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giovanni
 */
public class MateriePrime {

    public static List<TipoProdotto> getMateriePrime(TipoProdotto tipo) {

        switch (tipo) {
            case FARINA:
                return List.of(TipoProdotto.GRANO);
            case PANE:
                return List.of(TipoProdotto.FARINA, TipoProdotto.ACQUA);
            case CARNE:
                return List.of(TipoProdotto.MAIALE);
            case MAIALE:
                return List.of(TipoProdotto.GRANO, TipoProdotto.ACQUA);
            case ACQUA:
            case GRANO:
                break;
            case ASSE_LEGNO:
                return List.of(TipoProdotto.TRONCO);

        }
        return new ArrayList<>();
    }

}
