package com.posbeu.littletown.city.abs;

import com.posbeu.littletown.city.enums.TipoEdificio;

public abstract class Edificio {
    private final TipoEdificio tipoEdificio;

    public Edificio(TipoEdificio tipoEdificio) {
        this.tipoEdificio = tipoEdificio;
    }

    public TipoEdificio getTipoEdificio() {

        return tipoEdificio;
    }

    public abstract void work();
}
