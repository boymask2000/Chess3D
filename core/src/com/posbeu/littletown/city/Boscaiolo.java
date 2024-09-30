package com.posbeu.littletown.city;

import com.posbeu.littletown.city.abs.EdificoProduzione;
import com.posbeu.littletown.city.enums.TipoEdificio;
import com.posbeu.littletown.city.enums.TipoMerce;

public class Boscaiolo extends EdificoProduzione {
    public Boscaiolo() {
        super(TipoEdificio.BOSCAIOLO, TipoMerce.TRONCO, null);
    }
}
