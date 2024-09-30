package com.posbeu.littletown.city.abs;

import com.posbeu.littletown.city.enums.TipoEdificio;
import com.posbeu.littletown.city.enums.TipoMerce;
import com.posbeu.littletown.city.infrastruct.CentraleOrdini;
import com.posbeu.littletown.city.infrastruct.RegistroProduzioni;

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
    private int giacenze = 0;


    private TipoMerce merceProdotta;
    private Set<MateriaPrima> merceNecessaria = new HashSet<>();
    private Map<TipoMerce, MateriaPrima> merceDisponibile = new HashMap<>();
    private Map<TipoMerce, Integer> merceOrdinata = new HashMap<>();


    public EdificoProduzione(TipoEdificio tipoEdificio, //
                             TipoMerce merceProdotta, //
                             MateriaPrima merceNecessaria) {
        super(tipoEdificio);
        this.merceProdotta = merceProdotta;
        if (merceNecessaria != null)
            this.merceNecessaria.add(merceNecessaria);

    }


    public void work() {
        if (!isWorking()) return;
        if (new Date().getTime() - lastTimeWorking < delayWorking) return;
        lastTimeWorking = new Date().getTime();


        //Verificare giacenza
        if (giacenze >= maxGiacenza) return;


        //TODO verificare materie prime o ordinare
        if (!materiePresenti()) return;

        System.out.println(
                "Produco: " + merceProdotta + " " + new Date()
        );
        //produrre aggiornare giacenze
        aggiornaGiacenze();


        //TODO aggiornare materie prime
        agiornaMateriePrime();

        //TODO notifica produzione
        RegistroProduzioni.notifica(this, merceProdotta);
    }

    private void agiornaMateriePrime() {
        for (MateriaPrima m : merceNecessaria) {
            TipoMerce merceRichiesta = m.getTipoMerce();
            int qtaRichiesta = m.getQuantita();

            if (merceDisponibile.containsKey(merceRichiesta)) {
                MateriaPrima mm = merceDisponibile.get(merceRichiesta);
                mm.setQuantita(mm.getQuantita() - qtaRichiesta);
                merceDisponibile.put(merceRichiesta, mm);
            }
        }
    }

    private boolean materiePresenti() {
        boolean out = true;
        for (MateriaPrima m : merceNecessaria) {
            TipoMerce merceRichiesta = m.getTipoMerce();
            int qtaRichiesta = m.getQuantita();

            if (!merceDisponibile.containsKey(merceRichiesta) || merceDisponibile.get(merceRichiesta).getQuantita() +
                    merceOrdinata.get(merceRichiesta) < qtaRichiesta) {

                creaOrdine(merceRichiesta);
                out = false;
            }
        }

        return out;
    }

    private void creaOrdine(TipoMerce merceRichiesta) {
        Integer ord = merceOrdinata.get(merceRichiesta);
        if (ord == null) merceOrdinata.put(merceRichiesta, 1);
        else
            merceOrdinata.put(merceRichiesta, ord + 1);

        CentraleOrdini.creaOrdine(this, merceRichiesta);
    }

    private void aggiornaGiacenze() {
        giacenze++;
    }
}