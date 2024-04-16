package com.posbeu.littletown.engine.pezzi;


import com.posbeu.littletown.engine.Board;
import com.posbeu.littletown.engine.MossePossibili;
import com.posbeu.littletown.engine.Position;

public class Torre extends Pezzo {
    public Torre(Color colore) {
        super(PezziEnum.TORRE, colore);
    }


    @Override
    public void getMossePossibili(int i, int j, Board board, MossePossibili mossePossibili) {
        Position from = new Position(i, j);

        for (int dx = -1; dx < 2; dx++) {

            for (int dy = -1; dy < 2; dy++) {
                if (dx != 0 && dy != 0) continue;
                int ki = i;
                int kj = j;
                while (true) {
                    ki += dx;
                    kj += dy;
                    boolean proc = buildMossa(from, new Position(ki, kj), board, mossePossibili);
                    if (!proc) break;
                }
            }
        }

    }


}
