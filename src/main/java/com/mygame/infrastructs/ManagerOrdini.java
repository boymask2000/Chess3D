/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.infrastructs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giovanni
 */
public class ManagerOrdini {

    private static List<Ordine> ordini = new ArrayList<>();

    static public void add(Ordine ordine) {
        ordine.setWalkerOwner(0);
        ordini.add(ordine);

    }

    public synchronized static Ordine getOrdine(int idWalker) {
        for (Ordine ord : ordini) {
            if (ord.getWalkerOwner() == 0) {
                ord.setWalkerOwner(idWalker);
                return ord;
            }
        }
        return null;
    }

    static public void remove(Ordine ord) {
        ordini.remove(ord);
    }

    public static void dumpOrdini() {
        for (Ordine o : ordini) {
            System.out.println(o);
        }
    }

    public static void cleanLocks(int idWalker) {
        for (Ordine o : ordini) {
            if (o.getWalkerOwner() == idWalker) {
                o.setWalkerOwner(0);
            }
        }
    }
}
