/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.infrastructs;

import com.mygame.beni.TipoProdotto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author giovanni
 */
public class InventarioGlobale {

    private static List<GiacenzaInvGlobale> giacenze = new ArrayList<>();

    public static void add(GiacenzaInvGlobale giacenza) {
        giacenze.add(giacenza);
    }

    public synchronized static GiacenzaInvGlobale getGiacenza(TipoProdotto tipoProd, int idWalker) {
        for (GiacenzaInvGlobale g : giacenze) {
            if (g.getWalkerOwner() == 0 && g.getProdotto() == tipoProd) {
                g.setWalkerOwner(idWalker);
                return g;
            }
        }
        return null;
    }

    static public void remove(GiacenzaInvGlobale giac) {
        giacenze.remove(giac);
    }

    public static List<TipoProdotto> getListaProdotti() {
        List<TipoProdotto> l = new ArrayList<>();
        for (GiacenzaInvGlobale g : giacenze) {
            l.add(g.getProdotto());
        }

        return l;
    }

    public static GiacenzaInvGlobale getOldest() {
        if (giacenze.size() == 0) {
            return null;
        }
        GiacenzaInvGlobale ret = giacenze.get(0);

        Date now = new Date();
        long timeNow = now.getTime();
        if (timeNow - ret.getDataCreazione() < 20000) // 20 secondi
        {
            return null;
        }

        remove(ret);
        return ret;
    }

}
