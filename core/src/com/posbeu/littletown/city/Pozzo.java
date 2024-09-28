package com.posbeu.littletown.city;

import com.posbeu.littletown.city.abs.EdificoProduzione;
import com.posbeu.littletown.city.abs.MateriaPrima;
import com.posbeu.littletown.city.enums.TipoEdificio;
import com.posbeu.littletown.city.enums.TipoMerce;

import java.util.HashSet;

public class Pozzo extends EdificoProduzione {
    public Pozzo() {
        super(TipoEdificio.POZZZO, TipoMerce.SECCHHIO_ACQUA, null);
    }
}
