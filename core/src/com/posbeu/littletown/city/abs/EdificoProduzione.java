package com.posbeu.littletown.city.abs;

import com.posbeu.littletown.city.enums.TipoEdificio;
import com.posbeu.littletown.city.enums.TipoMerce;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class EdificoProduzione extends Edificio {
    private int maxGiacenza = 5;
    private int delayWorking = 2000; //millisecondi
    private long lastTimeWorking = 0;
    private Map<TipoMerce, Giacenza> giacenze = new HashMap<>();

    private List<TipoMerce> merceProdotta = new ArrayList<>();
    private Set<MateriaPrima> merceNecessaria = new HashSet<>();
    private Set<MateriaPrima> merceDisponibile = new HashSet<>();

    public EdificoProduzione(TipoEdificio tipoEdificio, //
                             List<TipoMerce> merceProdotta, //
                             Set<MateriaPrima> merceNecessaria) {
        super(tipoEdificio);
        this.merceProdotta = merceProdotta;
        this.merceNecessaria = merceNecessaria;
        initGiacenze();
    }

    public EdificoProduzione(TipoEdificio tipoEdificio, //
                             TipoMerce merceProdotta, //
                             MateriaPrima merceNecessaria) {
        super(tipoEdificio);
        this.merceProdotta.add(merceProdotta);
        if (merceNecessaria != null)
            this.merceNecessaria.add(merceNecessaria);
        initGiacenze();
    }

    private void initGiacenze() {
        for (TipoMerce tipoMerce : merceProdotta)
            giacenze.put(tipoMerce, new Giacenza(tipoMerce, 0));
    }

    public void work() {
        if (new Date().getTime() - lastTimeWorking < delayWorking) return;
        lastTimeWorking = new Date().getTime();


        //decidere cosa produrre ib base alla giacenza

        TipoMerce daProdurre = getTipoMerceDaProdurre();
        if (daProdurre == null) return;
        System.out.println(
                "Produco: " + daProdurre + " " + new Date()
        );
        //verificare materie prime o ordinare

        //produrre

        //aggiornare giacenze
        int giace = giacenze.get(daProdurre).getQuantita();
        System.out.println(
                giace
        );
        giacenze.put(daProdurre, new Giacenza(daProdurre, giace + 1));

        //aggiornare materie prime

        //richiedere trasporto
    }

    private TipoMerce getTipoMerceDaProdurre() {
        List<TipoMerce> oks = new ArrayList<>();
        for (TipoMerce tipoMerce : merceProdotta) {
            if (giacenze.get(tipoMerce).getQuantita() < maxGiacenza) {
                oks.add(tipoMerce);
            }
        }
        if (oks.size() == 0) return null;
        if (oks.size() == 1) return oks.get(0);
        Random r = new Random();
        int i = r.nextInt(oks.size());
        return oks.get(i);
    }

}
