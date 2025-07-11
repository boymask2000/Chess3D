/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.buildings.edifici;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.mygame.Pool;
import com.mygame.beni.TipoProdotto;
import com.mygame.buildings.TipoEdificio;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.component.QuadBackgroundComponent;
import java.util.List;

/**
 *
 * @author giovanni
 */
public abstract class Edificio {

    protected Giacenza giacenze = new Giacenza();

    protected boolean working = true;
    private Spatial spatial;
    private static int PROG_EDIFICI = 0;

    private final int idEdificio;
    private final Vector3f position;

    private final TipoEdificio tipoEdificio;

    private Geometry geometry;

    public Edificio(TipoEdificio tipo, Vector3f position) {
        this.tipoEdificio = tipo;
        Pool.addEdificio(this);
        this.position = position;
        idEdificio = getProgr();
        setLabel();
    }

    public TipoEdificio getTipoEdificio() {
        return tipoEdificio;
    }

    public Vector3f getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Edificio{" + "idEdificio=" + idEdificio + ", tipoEdificio=" + tipoEdificio + '}';
    }

    private static synchronized int getProgr() {
        PROG_EDIFICI++;
        return PROG_EDIFICI;
    }

    public void showPopupMenu() {

        Node node = null;
        if (geometry == null) {
            return;
        }

        // Rimuove eventuali menu esistenti.Qui elimino il menu se è mostrato. Da migliorare (fa cagare)
        if (Pool.getGuiNode().getChildren().size() > 1) {
            Pool.getGuiNode().detachChildAt(1);
        }
        Container menu = buildMenu();

        menu.setPreferredSize(new Vector3f(200, 150, 0)); // dimensione visibile
        menu.setBackground(new QuadBackgroundComponent(new ColorRGBA(0.1f, 0.1f, 0.1f, 1f)));

        Vector3f worldPos = getPosition(); // posizione 3D
        Vector3f screenPos = Pool.getCam().getScreenCoordinates(worldPos);
        menu.setLocalTranslation(screenPos.x, screenPos.y, 0);

        Pool.getGuiNode().attachChildAt(menu, 1);
    }

    protected Container buildMenu() {
        Container menu = new Container();
        menu.addChild(new Label(getTipoEdificio().name()));
        return menu;
    }

    public void setSpatial(Spatial s) {
        this.spatial = s;
    }

    public void setGeometries(List<Geometry> geometries) {

        if (geometries.size() > 0) {
            this.geometry = geometries.get(0);
        }
    }

    private void setLabel() {
        BitmapFont font = Pool.getAssetManager().loadFont("Interface/Fonts/Default.fnt");

// Crea il testo
        BitmapText label = new BitmapText(font);
        label.setText(tipoEdificio.toString());
        label.setSize(0.4f); // dimensione nel mondo
        label.setColor(ColorRGBA.White);

// Posiziona la label rispetto all'oggetto (es. leggermente sopra)
        label.setLocalTranslation(position.add(0, 3f, 0));

// Aggiungi alla scena
        Pool.getRootNode().attachChild(label);
    }

    public void takeProduct(TipoProdotto tipoProdRichiesto) {

        giacenze.remove(tipoProdRichiesto);
    }

    public void storeItem(TipoProdotto t) {

        giacenze.add(t);

    }

    public void update(float tpf) {
    }
}
