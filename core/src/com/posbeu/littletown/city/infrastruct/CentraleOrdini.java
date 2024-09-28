package com.posbeu.littletown.city.infrastruct;

import com.posbeu.littletown.city.abs.EdificoProduzione;
import com.posbeu.littletown.city.abs.Ordine;
import com.posbeu.littletown.city.enums.TipoMerce;

import java.util.ArrayList;
import java.util.List;

public class CentraleOrdini {

    private static List<Ordine> ordini = new ArrayList<>();

    public static void creaOrdine(EdificoProduzione edificio, TipoMerce merce) {
        Ordine ord = new Ordine(merce, edificio);
        System.out.println("ceato ordine " + merce);
        ordini.add(ord);
    }
}
