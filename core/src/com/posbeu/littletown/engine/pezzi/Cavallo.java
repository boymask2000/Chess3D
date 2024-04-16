package com.posbeu.littletown.engine.pezzi;


import com.posbeu.littletown.engine.Board;
import com.posbeu.littletown.engine.MossePossibili;
import com.posbeu.littletown.engine.Position;

public class Cavallo extends Pezzo {
    public Cavallo(Color colore) {
        super(PezziEnum.CAVALLO, colore);
    }

    @Override
    public void getMossePossibili(int i, int j, Board board, MossePossibili mossePossibili) {

        Position from = new Position(i, j);
        buildMossa(from, new Position(i + 1, j + 2), board, mossePossibili);
        buildMossa(from, new Position(i + 1, j - 2), board, mossePossibili);
        buildMossa(from, new Position(i - 1, j + 2), board, mossePossibili);
        buildMossa(from, new Position(i - 1, j - 2), board, mossePossibili);

        buildMossa(from, new Position(i + 2, j + 1), board, mossePossibili);
        buildMossa(from, new Position(i + 2, j - 1), board, mossePossibili);
        buildMossa(from, new Position(i - 2, j + 1), board, mossePossibili);
        buildMossa(from, new Position(i - 2, j - 1), board, mossePossibili);

    }

   /* private void buildMossa(Position from, Position to, Board b, MossePossibili mossePossibili) {
        int i = to.getI();
        int j = to.getJ();
        if (i < 0 || i > 7) return;
        if (j < 0 || j > 7) return;
        Pezzo p = b.getPezzo(i, j);
        if (p != null && p.getColore() == this.getColore()) return;
        Mossa m = new Mossa(this, from, to);


        mossePossibili.addMossa(m);
    }*/
}
