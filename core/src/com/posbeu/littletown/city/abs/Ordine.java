package com.posbeu.littletown.city.abs;

import com.posbeu.littletown.city.enums.TipoMerce;

public class Ordine {
    private TipoMerce merce;
    private EdificoProduzione edificio;

    public Ordine(TipoMerce merce, EdificoProduzione edificio) {
        this.merce = merce;
        this.edificio = edificio;
    }

    public TipoMerce getMerce() {
        return merce;
    }

    public EdificoProduzione getEdificio() {
        return edificio;
    }


}
