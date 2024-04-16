package com.posbeu.littletown.engine.pezzi;

public enum Color {
    BIANCO("B"),//
    NERO("N");


    private String code;

    Color(String code ){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
