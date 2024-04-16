package com.posbeu.littletown.engine.mosse;


import com.posbeu.littletown.engine.pezzi.Pezzo;
import com.posbeu.littletown.engine.Board;
import com.posbeu.littletown.engine.Position;

public class Arrocco extends Mossa {
    private Mossa mossaTorre;

    public Arrocco(Pezzo p, Position from, Position to) {
        super(p, from, to);
    }

    @Override
    public void esegui(Board board, boolean simulate) {
        super.esegui(board, simulate);  //eseguo mossa sul Re

        if (getFrom().getI() < getTo().getI()) {
            //Arrocco a destra
            Pezzo p = board.getPezzo(7, getFrom().getJ());
            if (p == null) return;
            mossaTorre = new Mossa(p, new Position(7, getFrom().getJ()), new Position(5, getFrom().getJ()));
            mossaTorre.esegui(board, simulate);
        }
    }

    @Override
    public String toString() {
        return "Arrocco "+super.toString();
    }

    @Override
    public void revert(Board board) {
        super.revert(board);
        mossaTorre.revert(board);

    }
}
