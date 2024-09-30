package com.posbeu.littletown.city.abs;

import com.posbeu.littletown.city.enums.TipoMerce;

public class Ordine {

    private int counter;
    private TipoMerce merce;
    private EdificoProduzione edificio;

    public Ordine(TipoMerce merce, EdificoProduzione edificio, int cou) {
        this.merce = merce;
        this.edificio = edificio;
        this.counter = cou;
    }

    public TipoMerce getMerce() {
        return merce;
    }

    public EdificoProduzione getEdificio() {
        return edificio;
    }

    public int getCounter() {
        return counter;
    }


    public void setCounter(int i) {
        counter = i;
    }
}
