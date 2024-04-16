package com.posbeu.littletown.engine.player;



import com.posbeu.littletown.engine.mosse.Mossa;
import com.posbeu.littletown.engine.Board;
import com.posbeu.littletown.engine.pezzi.Color;

import java.util.List;

public class Player {
    private static final int MAXVAL = 1000000;
    private static int maxDepth = 10;

    static int counter = 0;

    public static Mossa play(Color col, Board board) {
        Res r = new Res();
        counter = 0;
        //      playTree(col, board, maxDepth, false, r);

        playTreeAlfaBeta(col, board, maxDepth, new Res(-MAXVAL), new Res(MAXVAL), true, r);
        //    System.out.println(bestMove);
        // board.eseguiMossa(bestMove);
        return r.m;
    }

    /*  private static Res playTree(Color col, Board board, int depth, boolean go4Max, Res r) {

          if (depth > maxDepth) return null;
          int bestVal = 0;
          if (go4Max) {
              bestVal = -100000;
          } else {
              bestVal = 100000;
          }

          if (depth == 0) {
              r.val = board.valuta(col);
              return r;
          }
          List<Mossa> mp = board.getAllMosse(col);

          int i = 0;
          Mossa bestMove = null;
          for (Mossa m : mp) {
              counter++;
              System.out.println(counter);

              m.esegui(board,true);
              Res res = playTree(col, board, depth - 1, !go4Max, r);
              if (i == 0) {
                  bestVal = res.val;
                  bestMove = m;
                  i++;
              }
              m.revert(board);
              if (go4Max) {
                  if (res.val > bestVal) {
                      bestVal = res.val;
                      bestMove = m;

                  }
              } else {
                  if (res.val < bestVal) {
                      bestVal = res.val;
                      bestMove = m;

                  }

              }

          }
          r.m = bestMove;
          r.val = bestVal;
          return r;

      }
  */
    private static Res playTreeAlfaBeta(Color col, Board board, int depth, Res alfa, Res beta, boolean go4Max, Res r) {

        if (depth == 0) {
            r.val = board.valuta(col);

            return r;
        }
        List<Mossa> mp = board.getAllMosse(col);

        Res v;
        if (go4Max) {
            v = new Res(-MAXVAL);
            int id=9;
            for (Mossa m : mp) {

                m.esegui(board, true);
                v = max(m, v, playTreeAlfaBeta(invert(col), board, depth - 1, alfa, beta, !go4Max, r));

                m.revert(board);

                alfa = max(m, alfa, v);

                if (beta.minore(alfa)) break;
           //     v=alfa;
            }

        } else {
            v = new Res(MAXVAL);
            for (Mossa m : mp) {
                m.esegui(board, true);
                v = min(m, v, playTreeAlfaBeta(invert(col), board, depth - 1, alfa, beta, !go4Max, r));

                m.revert(board);

                beta = min(m, beta, v);
                if (beta.minore(alfa)) break;
            //    v=beta;
            }
        }

        return v;
    }

    private static Color invert(Color col) {
        if (col == Color.BIANCO) return Color.NERO;
        return Color.BIANCO;
    }

    private static Res max(Mossa m, Res v, Res res) {

        Res out = v;
        if (v.val < res.val) out = res;
        out.m = m;

        return out;
    }

    private static Res min(Mossa m, Res v, Res res) {
        Res out = v;
        if (v.val > res.val) out = res;
        out.m = m;
        return out;
    }
}
