/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.buildings.edifici;

import com.mygame.beni.TipoProdotto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author giovanni
 */
public class Giacenza {

    private final Map<TipoProdotto, Integer> giacenze = new HashMap<>();

    public void add(TipoProdotto t) {
      
        Integer val = giacenze.get(t);
      
        if (val == null) {
            giacenze.put(t, 1);
            return;
        }
        val++;
        giacenze.put(t, val);
   
      
    }

    public void remove(TipoProdotto t) {
        Integer val = giacenze.get(t);
      
        if (val == null) {
            return;
        }
        val--;
        giacenze.put(t, val);
        val = giacenze.get(t);
     
    }

    public Map<TipoProdotto, Integer> getGiacenze() {
        return giacenze;
    }

    public List<TipoProdotto> checkGiacenze(List<TipoProdotto> materiePrimePerProdurre) {
        List<TipoProdotto> missings = new ArrayList<>();

        Map<TipoProdotto, Integer> temp = new HashMap<>();

        for (TipoProdotto t : giacenze.keySet()) {
            temp.put(t, giacenze.get(t));
        }
        for (TipoProdotto t : materiePrimePerProdurre) {
      //      System.out.println("Check " + t);
            Integer val = temp.get(t);
       //     System.out.println("val " + val);
            if (val == null || val == 0) {
                missings.add(t);
                continue;
            }
            val--;
            temp.put(t, val);
        }
        return missings;
    }

}
