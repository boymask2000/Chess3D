package com.posbeu.littletown.city.enums;

public enum TipoMerce {
    TRONCO(5), //
    LEGNO(5), //
    SECCHHIO_ACQUA(5), //
    ASSE_LEGNO(1);

    private int tempo;

    TipoMerce(int tempo) {
        this.tempo = tempo;
    }

    public int getTempo() {
        return tempo;
    }
}
