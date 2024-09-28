package com.posbeu.littletown.city;

import com.posbeu.littletown.city.abs.EdificoProduzione;
import com.posbeu.littletown.city.abs.MateriaPrima;
import com.posbeu.littletown.city.enums.TipoEdificio;
import com.posbeu.littletown.city.enums.TipoMerce;

public class Segheria extends EdificoProduzione {
    public Segheria() {
        super(TipoEdificio.SEGHERIA, TipoMerce.ASSE_LEGNO, new MateriaPrima(TipoMerce.TRONCO, 1));
    }
}
