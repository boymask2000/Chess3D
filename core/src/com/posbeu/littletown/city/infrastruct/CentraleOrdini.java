package com.posbeu.littletown.city.infrastruct;

import com.posbeu.littletown.city.abs.EdificoProduzione;
import com.posbeu.littletown.city.abs.Ordine;
import com.posbeu.littletown.city.enums.TipoMerce;

import java.util.ArrayList;
import java.util.List;

public class CentraleOrdini {
    private static int counter = 0;

    private static List<Ordine> ordini = new ArrayList<>();

    public static void creaOrdine(EdificoProduzione edificio, TipoMerce merce) {
        Ordine ord = new Ordine(merce, edificio, counter++);
        System.out.println("ceato ordine " + merce);
        ordini.add(ord);
    }

    public static Ordine getOrdine() {
        if (ordini.size() == 0) return null;
        int min = ordini.get(0).getCounter();
        Ordine minOrdine = ordini.get(0);
        int index = 0;
        for (int i = 0; i < ordini.size(); i++) {
            if (ordini.get(i).getCounter() < min) {
                min = ordini.get(i).getCounter();
                minOrdine = ordini.get(i);
                index = i;
            }
        }
        ordini.remove(index);
        return minOrdine;

    }

    public static void restituisciOrdine(Ordine ordine) {
        ordine.setCounter(counter++);
        ordini.add(ordine);
    }
}
