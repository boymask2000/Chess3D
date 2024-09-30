package com.posbeu.littletown.city.infrastruct;

import com.posbeu.littletown.city.abs.EdificoProduzione;
import com.posbeu.littletown.city.enums.TipoMerce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RegistroProduzioni {
    private static Map<TipoMerce, Set<EdificoProduzione>> map = new HashMap<>();

    public static void notifica(EdificoProduzione edificoProduzione, TipoMerce merceProdotta) {
        Set<EdificoProduzione> ll = map.get(merceProdotta);
        if (ll == null) {
            ll = new HashSet<EdificoProduzione>();
            map.put(merceProdotta, ll);
        }
        ll.add(edificoProduzione);
    }

    public synchronized static EdificoProduzione getProduttore(TipoMerce merce) {
        Set<EdificoProduzione> ll = map.get(merce);
        if (ll == null) return null;
        Object[] rr = ll.toArray();
        EdificoProduzione ed = (EdificoProduzione) rr[0];
        ll.remove(ed);
        return ed;
    }
}
