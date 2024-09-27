package com.posbeu.littletown.terrain.vicinato;

import com.posbeu.littletown.terrain.Zolla;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vicino {
    public Zolla getZolla() {
        return zolla;
    }

    public void setZolla(Zolla zolla) {
        this.zolla = zolla;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    private Zolla zolla;
    private Direction dir;
}
