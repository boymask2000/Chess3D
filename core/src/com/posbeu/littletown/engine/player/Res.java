package com.posbeu.littletown.engine.player;


import com.posbeu.littletown.engine.mosse.Mossa;

public   class Res {
    Mossa m;
    int val;

    public Res(){

    }
    public Res(int y){
        val=y;
    }

    public boolean minore(Res alfa) {
        return val<=alfa.val;
    }
}