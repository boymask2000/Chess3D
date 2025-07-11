/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.buildings.edifici.coltivatore;

import com.mygame.base.commands.*;
import com.jme3.math.Vector3f;
import com.mygame.beni.TipoProdotto;
import com.mygame.buildings.edifici.coltivatore.EdificioColtivatore;

/**
 *
 * @author giovanni
 */
public class CommandMoveSemina extends CommandMove {

    private final Vector3f location;
    private final ColtivatoreNew owner;
    private TipoProdotto tipoProotto;
    private final EdificioColtivatore home;

    public CommandMoveSemina(EdificioColtivatore myHome, ColtivatoreNew owner, Vector3f destination, TipoProdotto tipoProdotto) {
        super(owner, destination);
        this.owner = owner;
        this.tipoProotto = tipoProdotto;
        this.home = myHome;
        this.location = destination;
    }

    @Override
    public void process() {
        home.startSemina();
    }

}
