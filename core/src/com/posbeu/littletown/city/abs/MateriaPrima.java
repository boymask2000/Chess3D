package com.posbeu.littletown.city.abs;

import com.posbeu.littletown.city.enums.TipoMerce;

public class MateriaPrima {
    private TipoMerce tipoMerce;


    private int quantita;


    public MateriaPrima(TipoMerce tipoMerce, int quantita) {
        this.tipoMerce = tipoMerce;
        this.quantita = quantita;
    }

    public TipoMerce getTipoMerce() {
        return tipoMerce;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
    public int getQuantita() {
        return quantita;
    }
}
