package com.posbeu.littletown;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.posbeu.littletown.component.BaseComponent;


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


        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans-Italic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels



        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();

//Set the font size (12 as an example)
        param.size = 18;
        style.font = generator.generateFont(param);

        Table table = new Table();table.row();
        TextButton headQuarter = new TextButton("HeadQuarter",style);
        TextButton headQuarter1 = new TextButton("HeadQuarter", skin);
        table.add(headQuarter).padBottom(10);//.padBottom(10);
        table.row();
        TextButton deposito = new TextButton("Deposito", style);
        table.add(deposito).padBottom(10);//.padBottom(10);
        table.row();
        TextButton pozzo = new TextButton("Pozzo", style);
        table.add(pozzo).padBottom(10);
        table.row();
        TextButton taglialegna = new TextButton("Taglialegna", style);
        table.add(taglialegna).padBottom(10);
        table.row();  table.row();
        TextButton forestale = new TextButton("Forestale", style);
        table.add(forestale).padBottom(10);
        //      table.row();
        TextButton segheria = new TextButton("Segheria", style);
        table.add(segheria).padBottom(10);

        table.setPosition(LittleTown.VIRTUAL_WIDTH-10, LittleTown.VIRTUAL_HEIGHT - 80);
        stage.addActor(table);
       headQuarter.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               System.out.println("ddd");
                BaseComponent ii = Pool.getInstances().get(0);
                ii.getInstance().transform.trn(new Vector3(10,10,10));
            }
        });
        /*
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
        generator.dispose();
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
}
