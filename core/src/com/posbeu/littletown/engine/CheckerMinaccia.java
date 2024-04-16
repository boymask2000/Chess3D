package com.posbeu.littletown.engine;


import com.posbeu.littletown.engine.pezzi.Cavallo;
import com.posbeu.littletown.engine.pezzi.Pezzo;
import com.posbeu.littletown.engine.pezzi.Alfiere;
import com.posbeu.littletown.engine.pezzi.Color;
import com.posbeu.littletown.engine.pezzi.Pedone;
import com.posbeu.littletown.engine.pezzi.Re;
import com.posbeu.littletown.engine.pezzi.Regina;
import com.posbeu.littletown.engine.pezzi.Torre;

import java.util.ArrayList;
import java.util.List;


public class CheckerMinaccia {
    public static List<Pezzo> isCellaMinacciata(int i, int j, Board board, Color myColor) {
        List<Pezzo> out = new ArrayList<>();
        Color col = Engine.avversario(myColor);

        checkCavalli(i, j, board, col, out);
        checkPedone(i, j, board, col, out);
        checkHorizontalVertical(i, j, board, col, out);
        checkDiagonal(i, j, board, col, out);
        checkRe(i, j, board, col, out);

        return out;
    }

    private static void checkRe(int i, int j, Board board, Color col, List<Pezzo> out) {
        checkSingoloRe(i - 1, j - 1, board, col, out);
        checkSingoloRe(i - 1, j, board, col, out);
        checkSingoloRe(i - 1, j + 1, board, col, out);
        checkSingoloRe(i, j - 1, board, col, out);
        checkSingoloRe(i, j + 1, board, col, out);
        checkSingoloRe(i + 1, j - 1, board, col, out);
        checkSingoloRe(i + 1, j, board, col, out);
        checkSingoloRe(i + 1, j + 1, board, col, out);


    }

    private static void checkSingoloRe(int i, int j, Board board, Color col, List<Pezzo> out) {
        Pezzo p = board.getPezzo(i, j);
        if (p != null) {
            if (p.getColore() == col && p.getClass() == Re.class)
                out.add(p);
        }

    }

    private static void checkDiagonal(int i, int j, Board board, Color col, List<Pezzo> out) {
        int x = i;
        int y = j;
        for (int k = 1; k < 7; k++) {
            Pezzo p = board.getPezzo(i + k, j + k);
            if (p != null) {

                if (p.getColore() == col && (p.getClass() == Alfiere.class || p.getClass() == Regina.class))
                    out.add(p);
                break;
            }
        }
        for (int k = 1; k < 7; k++) {
            Pezzo p = board.getPezzo(i - k, j - k);
            if (p != null) {

                if (p.getColore() == col && (p.getClass() == Alfiere.class || p.getClass() == Regina.class))
                    out.add(p);
                break;
            }
        }
        for (int k = 1; k < 7; k++) {
            Pezzo p = board.getPezzo(i - k, j + k);
            if (p != null) {

                if (p.getColore() == col && (p.getClass() == Alfiere.class || p.getClass() == Regina.class))
                    out.add(p);
                break;
            }
        }
        for (int k = 1; k < 7; k++) {
            Pezzo p = board.getPezzo(i + k, j - k);
            if (p != null) {

                if (p.getColore() == col && (p.getClass() == Alfiere.class || p.getClass() == Regina.class))
                    out.add(p);
                break;
            }
        }

    }

    private static void checkHorizontalVertical(int i, int j, Board board, Color col, List<Pezzo> out) {
        for (int x = i + 1; x < 8; x++) {
            Pezzo p = board.getPezzo(x, j);
            if (p != null) {

                if (p.getColore() == col && (p.getClass() == Torre.class || p.getClass() == Regina.class))
                    out.add(p);
                break;
            }
        }
        for (int x = i - 1; x >= 0; x--) {
            Pezzo p = board.getPezzo(x, j);
            if (p != null) {

                if (p.getColore() == col && (p.getClass() == Torre.class || p.getClass() == Regina.class))
                    out.add(p);
                break;
            }
        }
        for (int x = i + 1; x < 8; x++) {
            Pezzo p = board.getPezzo(i, x);
            if (p != null) {

                if (p.getColore() == col && (p.getClass() == Torre.class || p.getClass() == Regina.class))
                    out.add(p);
                break;
            }
        }
        for (int x = i - 1; x >= 0; x--) {
            Pezzo p = board.getPezzo(i, x);
            if (p != null) {

                if (p.getColore() == col && (p.getClass() == Torre.class || p.getClass() == Regina.class))
                    out.add(p);
                break;
            }
        }

    }

    private static void checkPedone(int i, int j, Board board, Color col, List<Pezzo> out) {
        int fact = 1;
        if (col == Color.NERO) fact = -1;
        checkTipo(i + 1, j + fact, board, col, Pedone.class, out);
        checkTipo(i - 1, j + fact, board, col, Pedone.class, out);

    }

    private static void checkCavalli(int i, int j, Board board, Color col, List<Pezzo> out) {
        checkCavallo(i + 1, j + 2, board, col, out);
        checkCavallo(i + 1, j - 2, board, col, out);
        checkCavallo(i - 1, j + 2, board, col, out);
        checkCavallo(i - 1, j - 2, board, col, out);
        checkCavallo(i + 2, j + 1, board, col, out);
        checkCavallo(i + 2, j - 1, board, col, out);
        checkCavallo(i - 2, j + 1, board, col, out);
        checkCavallo(i - 2, j - 1, board, col, out);

    }

    private static void checkCavallo(int i, int j, Board board, Color col, List<Pezzo> out) {
        Pezzo p = board.getPezzo(i, j);
        if (p instanceof Cavallo && p.getColore() == col) out.add(p);

    }

    private static void checkTipo(int i, int j, Board board, Color col, Class c, List<Pezzo> out) {
        Pezzo p = board.getPezzo(i, j);
        if (p != null && p.getClass() == c && p.getColore() == col) out.add(p);
    }
}
