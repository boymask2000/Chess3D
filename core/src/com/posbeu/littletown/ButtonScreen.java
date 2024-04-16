package com.posbeu.littletown;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class ButtonScreen {

    public Stage getStage() {
        return stage;
    }

    private final Stage stage;

    private ModelBatch modelBatch;

    private Engine engine;

    public ModelBuilder modelBuilder = new ModelBuilder();
    private PerspectiveCamera perspectiveCamera;

    private boolean setting= false;


//    private TipoEdificio edificioDaCreare = null;

    public ButtonScreen(LittleTown game) {

        stage = new Stage(new FitViewport(LittleTown.VIRTUAL_WIDTH, LittleTown.VIRTUAL_HEIGHT));

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table table = new Table();table.row();
        TextButton headQuarter = new TextButton("HeadQuarter", skin);
        table.add(headQuarter).padBottom(10);//.padBottom(10);
        table.row();
        TextButton deposito = new TextButton("Deposito", skin);
        table.add(deposito).padBottom(10);//.padBottom(10);
        table.row();
        TextButton pozzo = new TextButton("Pozzo", skin);
        table.add(pozzo).padBottom(10);
        table.row();
        TextButton taglialegna = new TextButton("Taglialegna", skin);
        table.add(taglialegna).padBottom(10);
        table.row();  table.row();
        TextButton forestale = new TextButton("Forestale", skin);
        table.add(forestale).padBottom(10);
        //      table.row();
        TextButton segheria = new TextButton("Segheria", skin);
        table.add(segheria).padBottom(10);

        table.setPosition(80, LittleTown.VIRTUAL_HEIGHT - 80);
        stage.addActor(table);
       /* headQuarter.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setting = !setting;
                edificioDaCreare = TipoEdificio.HEADQUARTER;
            }
        });
        deposito.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setting = !setting;
                edificioDaCreare = TipoEdificio.DEPOSITO;
            }
        });
        pozzo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setting = !setting;
                edificioDaCreare = TipoEdificio.POZZO;
            }
        });
        taglialegna.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setting = !setting;
                edificioDaCreare = TipoEdificio.TAGLIALEGNA;
            }
        });
        forestale.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setting = !setting;
                edificioDaCreare = TipoEdificio.FORESTALE;
            }
        });
        segheria.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setting = !setting;
                edificioDaCreare = TipoEdificio.SEGHERIA;
            }
        });*/
    }

    public void remove(Entity entity) {
        engine.removeEntity(entity);

    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    public void dispose() {

        modelBatch.dispose();
        modelBatch = null;
    }

/*    public TipoEdificio getEdificioDaCreare() {
        return edificioDaCreare;
    }

    public void resetEdificioDaCreare() {
        edificioDaCreare=null;
    }*/
}
