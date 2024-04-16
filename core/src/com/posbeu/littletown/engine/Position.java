package com.posbeu.littletown.engine;


import com.posbeu.littletown.engine.pezzi.Pezzo;
import com.posbeu.littletown.engine.player.BoardGDX;

import java.util.Objects;

public class Position {



    private Pezzo obj = null;
    private int i;
    private int j;

    private float floatI;
    private float floatJ;

    public Position() {

    }

    public Position(int i, int j) {
        this.i = i;
        this.j = j;
    }


    public Position(Pezzo obj, int i, int j) {
        this.obj = obj;
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return getI() == position.getI() && getJ() == position.getJ();
    }

    public char getIChar() {
        if (i < 8)
            return "ABCDEFGH".charAt(i);
        return ' ';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getI(), getJ());
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
        floatI= BoardGDX.convertPosToCoord(i);
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
        floatJ=BoardGDX.convertPosToCoord(j);
    }

    public Pezzo getObj() {
        return this.obj;
    }

    public float getFloatI() {
        return floatI;
    }

    public void setFloatI(float floatI) {
        this.floatI = floatI;
    }

    public float getFloatJ() {
        return floatJ;
    }

    public void setFloatJ(float floatJ) {
        this.floatJ = floatJ;
    }
    public void setObj(Pezzo obj) {
        this.obj = obj;
    }
    @Override
    public String toString() {
        return "Position{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}
