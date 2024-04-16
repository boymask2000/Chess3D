package com.posbeu.littletown.engine;



import com.posbeu.littletown.engine.pezzi.Pezzo;
import com.posbeu.littletown.engine.mosse.Arrocco;
import com.posbeu.littletown.engine.mosse.Mossa;
import com.posbeu.littletown.engine.pezzi.Color;
import com.posbeu.littletown.engine.pezzi.PezziEnum;

public class MossaBuilder {
    private static final String H = "ABCDEFGH";

    public static Mossa buildMossaFromInput(String sMove, Color playerColor, Board board) {

        if (sMove == null || sMove.trim().length() != 4) return null;
        char c_i = sMove.charAt(0);
        int from_i = H.indexOf(c_i);
        int from_j = Integer.parseInt("" + sMove.charAt(1)) - 1;
        char d_i = sMove.charAt(2);
        int to_i = H.indexOf(d_i);
        int to_j = Integer.parseInt("" + sMove.charAt(3)) - 1;
        Pezzo p = board.getPezzo(from_i, from_j);
        if (p == null) return null;
        if (p.getColore() != playerColor) return null;
        if (p.getPezzoEnum() == PezziEnum.RE) {
            if (Math.abs(from_i - to_i) == 2)
                return new Arrocco(p, new Position(from_i, from_j), new Position(to_i, to_j));
        }
        return new Mossa(p, new Position(from_i, from_j), new Position(to_i, to_j));
    }

}
