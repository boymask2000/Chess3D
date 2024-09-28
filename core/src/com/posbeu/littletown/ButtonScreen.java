package com.posbeu.littletown;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class ButtonScreen implements Screen {

    private final Stage stage;
    private final PerspectiveCamera camera;

    private ModelBatch modelBatch;

    private Engine engine;
    private Table table = new Table();
    public ModelBuilder modelBuilder = new ModelBuilder();
    private PerspectiveCamera perspectiveCamera;


    public ButtonScreen(ChessGame game) {
        this.camera = Pool.getCamera();

        stage = new Stage(new FitViewport(ChessGame.VIRTUAL_WIDTH, ChessGame.VIRTUAL_HEIGHT));
        stage.addActor(table);

        InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        inputMultiplexer.addProcessor(stage);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans-Italic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels


        setButton();
/*        createLabel("Computer",0,0);
        createLabel("Human",0,1);*/

        generator.dispose();
    }

    private void setButton() {
        table.setPosition(50, ChessGame.VIRTUAL_HEIGHT - 100);

        TextButton falegnameButton = new TextButton("Falegname", Assets.skin);

        TextButton boscaioloButton = new TextButton("Boscaiolo", Assets.skin);
        TextButton pozzoButton = new TextButton("Pozzo", Assets.skin);
        TextButton segheriaButton = new TextButton("Segheria", Assets.skin);


        falegnameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Pool.setSelectedButton(TipoOggetto.FALEGNAME);
            }
        });
        boscaioloButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Pool.setSelectedButton(TipoOggetto.BOSCAIOLO);
            }
        });
        pozzoButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Pool.setSelectedButton(TipoOggetto.POZZO);
            }
        });
        segheriaButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Pool.setSelectedButton(TipoOggetto.SEGHERIA);
            }
        });

        addButton(falegnameButton);
        addButton(boscaioloButton);
        addButton(pozzoButton);
        addButton(segheriaButton);

    }

    private void addButton(TextButton b) {
        table.add(b).width(100).height(90).padBottom(5);
        table.row();
    }

    private void setTable(FreeTypeFontGenerator generator) {
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();


        // tw.setPosition(0,30);
        //     stage.addActor(tw);
//Set the font size (12 as an example)
        param.size = 18;
        style.font = generator.generateFont(param);

        Table table = new Table();

        table.row();
        TextButton headQuarter = new TextButton("HeadQuarter", style);
        TextButton headQuarter1 = new TextButton("HeadQuarter", skin);
        // table.add(headQuarter).padBottom(10);//.padBottom(10);
        table.add(headQuarter).padBottom(10);//.


        table.row();
        TextButton deposito = new TextButton("Deposito", style);
        table.add(deposito).padBottom(10);//.padBottom(10);
        table.row();
        TextButton pozzo = new TextButton("Pozzo", style);
        table.add(pozzo).padBottom(10);
        table.row();
        TextButton taglialegna = new TextButton("Taglialegna", style);
        table.add(taglialegna).padBottom(10);
        table.row();
        table.row();
        TextButton forestale = new TextButton("Forestale", style);
        table.add(forestale).padBottom(10);
        //      table.row();
        TextButton segheria = new TextButton("Segheria", style);
        table.add(segheria).padBottom(10);

        table.setPosition(100, ChessGame.VIRTUAL_HEIGHT - 80);
        stage.addActor(table);
        headQuarter.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("ddd");
               /* BaseComponent ii = Pool.getInstances().get(0);
                ii.getInstance().transform.trn(new Vector3(10,10,10));*/
            }
        });
    }

    private void createLabel(String s, int x, int y) {
        int Help_Guides = 12;
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans-Italic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.borderWidth = 1;
        parameter.color = Color.YELLOW;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = 3;
        //  parameter.shadowColor = new Color(0, 0.5f, 0, 0.75f);
        BitmapFont font24 = generator.generateFont(parameter); // font size 24 pixels
        generator.dispose();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font24;

        Label label2 = new Label(s, labelStyle);
        label2.setSize(Gdx.graphics.getWidth() / Help_Guides * 5, row_height);
        label2.setPosition(col_width * (x + 13), Gdx.graphics.getHeight() - row_height * y);

        stage.addActor(label2);

  /*      label2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
              System.out.println("kkkk");
            }
        });*/
    }

    public void remove(Entity entity) {
        engine.removeEntity(entity);

    }

    @Override
    public void show() {

    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void dispose() {

        modelBatch.dispose();
        modelBatch = null;
    }
}
