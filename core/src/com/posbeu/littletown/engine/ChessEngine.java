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
        board.init();
        BoardGDX.setBoard(board);
    }

 /*   public void start() {


        board.init();
        board.print(playerColor);

        while (true) {
            String sMove = null;
            Mossa playerMossa = null;
            while (true) {
                try {
                    sMove = readInput();

                    playerMossa = MossaBuilder.buildMossaFromInput(sMove, playerColor, board);
                    System.out.println(playerMossa);
                    if (checkMossa(playerMossa, playerColor, board)) break;
                    System.out.println("invalid");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            board.eseguiMossaOK(playerMossa);

            board.print(playerColor);

            Mossa mossaAvversario = Player.play(avversario(playerColor), board);
            board.eseguiMossaOK(mossaAvversario);
            board.print(playerColor);
        }
    }
*/

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


 /*   private String readInput() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Reading data using readLine
        return reader.readLine().toUpperCase();
    }*/

    public static Color avversario(Color c) {
        if (c == Color.BIANCO) return Color.NERO;
        return Color.BIANCO;
    }


    public void stampaMinacce(Position pos) {
        if (pos.getObj() == null) return;
        List<Pezzo> m = board.getMinacce(pos.getI(), pos.getJ(), pos.getObj().getColore());
        for (int i = 0; i < m.size(); i++)
            System.out.println(pos.getI()+" "+pos.getJ()+" Minaccia: " + m.get(i));
    }
}