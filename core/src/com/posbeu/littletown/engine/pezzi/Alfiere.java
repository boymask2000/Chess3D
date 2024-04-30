package com.posbeu.littletown.engine.pezzi;


import com.badlogic.gdx.graphics.g3d.Model;
import com.posbeu.littletown.engine.Board;
import com.posbeu.littletown.engine.MossePossibili;
import com.posbeu.littletown.engine.BoardPosition;

public class Alfiere extends Pezzo {
    public Alfiere(Color colore) {
        super(PezziEnum.ALFIERE, colore);
    }

    @Override
    public void getMossePossibili(int i, int j, Board board, MossePossibili mossePossibili) {
        BoardPosition from = new BoardPosition(i, j);

        for (int dx = -1; dx < 2; dx++) {
            if (dx == 0) continue;
            for (int dy = -1; dy < 2; dy++) {
                if (dy == 0) continue;
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

    @Override
    public Model getModel() {
        return getModel("alfiere");
    }
}
