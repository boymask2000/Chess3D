package com.posbeu.littletown.engine.pezzi;

public enum PezziEnum {

    PEDONE(1, "P"), //
    TORRE(10,"T"), //
    ALFIERE(5,"A"), //
    CAVALLO(5,"C"),//
    RE(100000,"R"),//
    REGINA(1000,"Q");


    private int valore;


    private String code;
    PezziEnum(int val, String code) {
        this.valore = val;
        this.code=code;
    }
    public String getCode() {
        return code;
    }

    public int getValore() {
        return valore;
    }
}
