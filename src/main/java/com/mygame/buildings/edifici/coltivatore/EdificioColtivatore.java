/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.buildings.edifici.coltivatore;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.mygame.beni.TipoProdotto;
import com.mygame.buildings.TipoEdificio;
import com.mygame.buildings.edifici.Edificio;
import com.mygame.infrastructs.GiacenzaInvGlobale;
import com.mygame.infrastructs.InventarioGlobale;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;

/**
 *
 * @author giovanni
 */
public class EdificioColtivatore extends Edificio {

    private final TipoProdotto tipoProdotto;
    private Vector3f centerArea;
    private int giacenza;
    private CampoColtivatoGrano campo;

    public EdificioColtivatore(TipoEdificio tipo, Vector3f position) {
        super(tipo, position);

        this.tipoProdotto = tipo.getProdotto();

        init();
    }

    private void init() {
        centerArea = getRandomPos(getPosition(), 15f);

//        new Coltivatore(this, centerArea, tipoProdotto);
        Vector3f positionCampo = getRandomPos(getPosition(), 15f);

        campo = new CampoColtivatoGrano(positionCampo, 5);
        new ColtivatoreNew(this, positionCampo, tipoProdotto);
    }

    public Vector3f getRandomPos(Vector3f pos, float radius) {

        float offsetX = (FastMath.nextRandomFloat() * 2 - 1) * radius;
        float offsetZ = (FastMath.nextRandomFloat() * 2 - 1) * radius;

        return pos.add(offsetX, 0, offsetZ);
    }

    @Override
    protected Container buildMenu() {

        Container menu = super.buildMenu();
        menu.addChild(new Label("-----Produzione----------------"));

        menu.addChild(new Label(tipoProdotto.toString() + ": " + getGiacenza()));

        return menu;
    }

    @Override
    public void storeItem(TipoProdotto t) {
        giacenza++;
        InventarioGlobale.add(new GiacenzaInvGlobale(this, t));
    }

    public int getGiacenza() {
        return giacenza;
    }

    @Override
    public void takeProduct(TipoProdotto tipoProdRichiesto) {

        giacenza--;
    }

    void startSemina() {
        campo.startSemina();
    }

    public Vector3f getPositionCampo() {
        return campo.getPosition();
    }

}
