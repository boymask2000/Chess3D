package com.posbeu.littletown.engine.player;


import com.posbeu.littletown.engine.pezzi.Color;
import com.posbeu.littletown.engine.pezzi.Pezzo;
import com.posbeu.littletown.engine.Board;

public class Valutatore {
    private static final int[] valori = {1, 2, 3, 4, 4, 3, 2, 1};
    private final Board board;

    public Valutatore(Board board) {
        this.board = board;
    }


    public int valuta(Color color) {
        int valore = 0;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                Pezzo p = board.getPezzo(i, j);
                if (p != null) {
                    int val = p.getValore();
                    valore += p.getColore() == color ? val : -val;

                    valore += p.getColore() == color ?  valori[i] + valori[j] : //
                                                        -valori[i] - valori[j];
                }

            }
        return valore;
    }
}
