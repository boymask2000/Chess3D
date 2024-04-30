package com.posbeu.littletown.engine;


import com.posbeu.littletown.engine.pezzi.Pezzo;
import com.posbeu.littletown.engine.mosse.Mossa;
import com.posbeu.littletown.engine.pezzi.Color;
import com.posbeu.littletown.engine.player.BoardGDX;
import com.posbeu.littletown.engine.player.Player;

import java.util.List;


public class ChessEngine {


    private Board board = new Board();
    private Color playerColor = Color.BIANCO;

    public static void main(String[] args) {
        ChessEngine engine = new ChessEngine();

        //     engine.start();
    }

    public ChessEngine() {
        //      board.init();
        BoardGDX.setBoard(board);
    }

    public void dump() {
        board.dump();
    }

    public Mossa play(Mossa m) {

        m.esegui(board, false);
        Mossa mossaAvversario = Player.play(avversario(playerColor), board);
        mossaAvversario.esegui(board, false);
        return mossaAvversario;
    }

    public void eseguiMossa(Mossa m) {
        // board.eseguiMossaOK(m);
        m.esegui(board, false);
    }

    public Mossa play() {
        return Player.play(avversario(playerColor), board);
    }

    public Mossa processMossa(String sMove) {
        Mossa playerMossa = MossaBuilder.buildMossaFromInput(sMove, playerColor, board);

        if (playerMossa == null) return null;
        if (!checkMossa(playerMossa, playerColor, board)) return null;

        return playerMossa;
    }

    private boolean checkMossa(Mossa playerMossa, Color playerColor, Board board) {
        List<Mossa> l = board.getAllMosse(playerColor);
        for (Mossa m : l) if (m.equals(playerMossa)) return true;
        return false;
    }

    public List<Mossa> getMosse(Pezzo p, int i, int j) {
        MossePossibili out = new MossePossibili(board);
        p.getMossePossibili(i, j, board, out);
        return out.getMossePossibili();

    }

    public static Color avversario(Color c) {
        if (c == Color.BIANCO) return Color.NERO;
        return Color.BIANCO;
    }


    public void stampaMinacce(BoardPosition pos) {
        if (pos.getObj() == null) return;
        List<Pezzo> m = board.getMinacce(pos.getI(), pos.getJ(), pos.getObj().getColore());
        for (int i = 0; i < m.size(); i++)
            System.out.println(pos.getI() + " " + pos.getJ() + " Minaccia: " + m.get(i));
    }
    public Board getBoard() {
        return board;
    }

    public void setPezzo(Pezzo pezzo, int i, int j) {
        board.setPezzo(i, j, pezzo);
    }
}