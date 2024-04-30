package com.posbeu.littletown.engine.pezzi;


import com.badlogic.gdx.graphics.g3d.Model;
import com.posbeu.littletown.engine.Board;
import com.posbeu.littletown.engine.MossePossibili;
import com.posbeu.littletown.engine.BoardPosition;

public class Regina extends Pezzo {
    public Regina(Color colore) {
        super(PezziEnum.REGINA, colore);
    }


    @Override
    public void getMossePossibili(int i, int j, Board board, MossePossibili mossePossibili) {
        BoardPosition from = new BoardPosition(i, j);

        for (int dx = -1; dx < 2; dx++) {

            for (int dy = -1; dy < 2; dy++) {
                if (dx == 0 && dy == 0) continue;
                int ki = i;
                int kj = j;
                while (true) {
                    ki += dx;
                    kj += dy;
                    boolean proc = buildMossa(from, new BoardPosition(ki, kj), board, mossePossibili);
                    if (!proc) break;
                }
            }
        }

    }

   /* private boolean buildMossa(Position from, Position to, Board b, MossePossibili mossePossibili) {
        int i = to.getI();
        int j = to.getJ();
        if (i < 0 || i > 7) return false;
        if (j < 0 || j > 7) return false;
        Pezzo p = b.getPezzo(i, j);
        if (p != null) {
            if (p.getColore() != this.getColore()) {
                Mossa m = new Mossa(this, from, to);
                mossePossibili.addMossa(m);
            }
            return false;
        }
        Mossa m = new Mossa(this, from, to);

        mossePossibili.addMossa(m);
        return true;
    }*/
   @Override
   public Model getModel() {
       return getModel("regina");
   }
}
