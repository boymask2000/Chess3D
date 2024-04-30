package com.posbeu.littletown.engine;

import com.posbeu.littletown.engine.mosse.Mossa;
import com.posbeu.littletown.engine.pezzi.Alfiere;
import com.posbeu.littletown.engine.pezzi.Cavallo;
import com.posbeu.littletown.engine.pezzi.Color;
import com.posbeu.littletown.engine.pezzi.Pedone;
import com.posbeu.littletown.engine.pezzi.Pezzo;
import com.posbeu.littletown.engine.pezzi.Re;
import com.posbeu.littletown.engine.pezzi.Regina;
import com.posbeu.littletown.engine.player.Valutatore;
import com.posbeu.littletown.engine.pezzi.PezziEnum;
import com.posbeu.littletown.engine.pezzi.Torre;

import java.util.List;

public class Board {
    private Pezzo[][] board = new Pezzo[8][8];

    private Valutatore valutatore;

    public Board() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                board[i][j] = null;

        valutatore = new Valutatore(this);
    }
    public void dump() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pezzo p = board[i][j];
                if (p == null)
                    System.out.print("  ");
                else
                    System.out.print(p.getCode());
            } System.out.println();
        }
        valutatore = new Valutatore(this);
    }
    public Pezzo getPezzo(int i, int j) {
        if (i < 0 || i > 7) return null;
        if (j < 0 || j > 7) return null;
        return board[i][j];
    }

    public Pezzo getPezzo(BoardPosition pos) {
        return board[pos.getI()][pos.getJ()];
    }

    public void setPezzo(int i, int j, Pezzo p) {
        board[i][j] = p;
    }

    public void setPezzo(BoardPosition pos, Pezzo p) {
        board[pos.getI()][pos.getJ()] = p;
    }

    public void init() {
        board[0][0] = new Torre(Color.BIANCO);
        board[1][0] = new Cavallo(Color.BIANCO);
        board[2][0] = new Alfiere(Color.BIANCO);
        board[3][0] = new Regina(Color.BIANCO);
        board[4][0] = new Re(Color.BIANCO);
        board[5][0] = new Alfiere(Color.BIANCO);
        board[6][0] = new Cavallo(Color.BIANCO);
        board[7][0] = new Torre(Color.BIANCO);
        for (int i = 0; i < 8; i++) board[i][1] = new Pedone(Color.BIANCO);

        board[0][7] = new Torre(Color.NERO);
        board[1][7] = new Cavallo(Color.NERO);
        board[2][7] = new Alfiere(Color.NERO);
        board[3][7] = new Regina(Color.NERO);
        board[4][7] = new Re(Color.NERO);
        board[5][7] = new Alfiere(Color.NERO);
        board[6][7] = new Cavallo(Color.NERO);
        board[7][7] = new Torre(Color.NERO);
        for (int i = 0; i < 8; i++) board[i][6] = new Pedone(Color.NERO);
    }

    public boolean isReMinacciato(Mossa m, Color color) {
        BoardPosition p = cercaRe(color);
        boolean b = isCellaMinacciata(p.getI(), p.getJ(), color);

        return b;

    }

    public boolean isCellaMinacciata(int i, int j, Color color) {
        List<Pezzo> minacce = CheckerMinaccia.isCellaMinacciata(i, j, this, color);

        return !minacce.isEmpty();

    }
    public List<Pezzo> getMinacce(int i, int j, Color color) {
        return CheckerMinaccia.isCellaMinacciata(i, j, this, color);

    }

    public BoardPosition cercaRe(Color color) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                Pezzo p = getPezzo(i, j);
                if (p != null && p.getColore() == color && p.getPezzoEnum() == PezziEnum.RE)
                    return new BoardPosition(i, j);
            }
        return null;
    }

   /* public void eseguiMossa(Mossa m, boolean simulate) {

        m.setPrevAlreadyMoved(m.getPezzo().isAlreadyMoved());
        m.getPezzo().setAlreadyMoved(true);
        m.setPezzoEliminato(getPezzo(m.getTo()));
        setPezzo(m.getTo(), m.getPezzo());
        setPezzo(m.getFrom(), null);
    }

    public void eseguiMossaOK(Mossa m) {

        Anagrafica.spostaPezzoActor(m);
        eseguiMossa(m);

    }*/


    public List<Mossa> getAllMosse(Color color) {
        MossePossibili mossePossibili = new MossePossibili(this);


        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                Pezzo p = getPezzo(i, j);
                if (p != null && p.getColore() == color) {
                    p.getMossePossibili(i, j, this, mossePossibili);

                }
            }
        return mossePossibili.getMossePossibili();
    }

    public int valuta(Color col) {
        return valutatore.valuta(col);
    }

    public boolean isEmpty(int i, int y) {
        return board[i][y]==null;
    }
}
