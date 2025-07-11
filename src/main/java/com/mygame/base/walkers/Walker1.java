/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.base.walkers;

import com.mygame.infrastructs.Ordine;
import com.mygame.infrastructs.GiacenzaInvGlobale;
import com.mygame.infrastructs.ManagerOrdini;
import com.mygame.infrastructs.InventarioGlobale;
import com.mygame.base.commands.CommandMove;
import com.mygame.base.commands.CommandMoveGet;
import com.mygame.base.commands.CommandMovePut;
import com.mygame.buildings.edifici.*;
import com.jme3.math.Vector3f;
import com.mygame.beni.TipoProdotto;
import com.mygame.catasto.InventarioEdifici;
import java.util.List;

/**
 *
 * @author giovanni
 */
public class Walker1 extends Walker0 {

    private enum Stato {
        RETURNING_HOME, LOOKING, GOING_TO_FORNITORE, GOING_TO_RICHIEDENTE
    };

    private Edificio edRichiedente;
    private Edificio edFornitore;
    private TipoProdotto tipoProdRichiesto;

    public Walker1(Edificio ed) {
        super(ed);

    }

    @Override
    public void processStanding() {

        boolean foundOrdine = searchMerceForOrders();
        boolean foundForDepositi = searchMerceForDepositi();

        if (!foundOrdine) {

            startPos = groupNode.getLocalTranslation();

            CommandMove c1 = new CommandMove(this, home.getPosition());
            addCommand(c1);
        }

    }

    private boolean searchMerceForDepositi() {
        GiacenzaInvGlobale giac = InventarioGlobale.getOldest();
        if (giac == null) {
            return false;
        }
        Edificio deposito = InventarioEdifici.searchDeposito(getNode().getLocalTranslation());
        if (deposito == null) {
            return false;
        }

        CommandMoveGet c1 = new CommandMoveGet(this, giac.getEdificio().getPosition(), giac.getEdificio(), giac.getProdotto());
        addCommand(c1);
        CommandMovePut c2 = new CommandMovePut(this, deposito.getPosition(), deposito, giac.getProdotto());
        addCommand(c2);

        return true;
    }

    private boolean searchMerceForOrders() {
        boolean found = false;
        Ordine ord;
        do {
            ord = ManagerOrdini.getOrdine(idWalker);

            if (ord != null) {
                edRichiedente = ord.getEdificio();

                tipoProdRichiesto = ord.getProdotto();
                GiacenzaInvGlobale giac = InventarioGlobale.getGiacenza(tipoProdRichiesto, idWalker);
                if (giac != null) {

                    startPos = groupNode.getLocalTranslation();

                    edFornitore = giac.getEdificio();
                    CommandMoveGet c1 = new CommandMoveGet(this, edFornitore.getPosition(), edFornitore, tipoProdRichiesto);
                    addCommand(c1);
                    CommandMovePut c2 = new CommandMovePut(this, edRichiedente.getPosition(), edRichiedente, tipoProdRichiesto);
                    addCommand(c2);
                    // endPos = edFornitore.getPosition();
                    found = true;

                    control.setCurrentAction("Walk");
                    ManagerOrdini.remove(ord);
                    InventarioGlobale.remove(giac);
                    break;
                }
            }

        } while (ord != null);
        ManagerOrdini.cleanLocks(idWalker);
        return found;
    }
}
