package com.posbeu.littletown.city.abs;

import com.posbeu.littletown.city.enums.TipoEdificio;
import com.posbeu.littletown.terrain.Zolla;

public abstract class Edificio {

    private boolean working = false;

    private final TipoEdificio tipoEdificio;
    private Zolla zolla;

    public Edificio(TipoEdificio tipoEdificio) {
        this.tipoEdificio = tipoEdificio;
    }

    public TipoEdificio getTipoEdificio() {

        return tipoEdificio;
    }

    public abstract void work();

    public void setZolla(Zolla zolla) {
        this.zolla = zolla;
    }

    public Zolla getZolla() {
        return zolla;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

}
