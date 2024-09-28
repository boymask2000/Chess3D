package com.posbeu.littletown.city;

import com.posbeu.littletown.city.abs.Edificio;
import com.posbeu.littletown.city.enums.TipoEdificio;

public class Deposito extends Edificio {
    public Deposito(TipoEdificio tipoEdificio) {
        super(tipoEdificio);
    }

    @Override
    public void work() {

    }
}
