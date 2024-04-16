package com.posbeu.littletown.engine;


import com.posbeu.littletown.engine.mosse.Mossa;

import java.util.ArrayList;
import java.util.List;

public class MossePossibili {
    private final Board board;


    List<Mossa> out = new ArrayList<>();

    public MossePossibili(Board board) {
        this.board = board;
    }

    public void addMossa(Mossa m) {

       m.esegui(board, true);
       if (!board.isReMinacciato(m, m.getPezzo().getColore()))
            out.add(m);
       m.revert(board);
      //  board.revertMossa(m);
    }

    public List<Mossa> getMossePossibili() {

        return out;
    }
}
