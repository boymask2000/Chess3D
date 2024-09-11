package com.posbeu.littletown;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class StartScreen implements Screen {

    public Stage getStage() {
        return stage;
    }

    private final Stage stage;

    private ModelBatch modelBatch;


    public StartScreen(ChessGame game) {

        stage = new Stage(new FitViewport(ChessGame.VIRTUAL_WIDTH, ChessGame.VIRTUAL_HEIGHT));


        BitmapFont font12 = getFont();

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();

        style.font = font12;//generator.generateFont(param);

        Table table = new Table();
        table.row();
        TextButton startGame = new TextButton("Start", style);
        startGame.setSize(200,50);
        table.add(startGame).padBottom(10);//.padBottom(10);
        table.row();

        startGame.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.log("Click", "performed"); // -> never happend
            }
        });

        startGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {

                Gdx.app.log("Click", "performed"); // -> never happend
            }
        });
        startGame.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println();return true;
            }
        });



        /*TextButton deposito = new TextButton("Deposito", style);
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


*/

        table.setPosition(ChessGame.VIRTUAL_WIDTH - 50, ChessGame.VIRTUAL_HEIGHT - 80);
        stage.addActor(table);

        createLabel("Computer", 0, 0);
        createLabel("Human", 0, 1);

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
        //     generator.dispose();
    }

    private void createLabel(String s, int x, int y) {
        int Help_Guides = 12;
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;

/*        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans-Italic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.borderWidth = 1;
        parameter.color = Color.YELLOW;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = 3;
      //  parameter.shadowColor = new Color(0, 0.5f, 0, 0.75f);
        BitmapFont font24 = generator.generateFont(parameter); // font size 24 pixels
        generator.dispose();
*/

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = getFont();//font24;

        Label label2 = new Label(s, labelStyle);
        label2.setSize(Gdx.graphics.getWidth() / Help_Guides * 5, row_height);
        label2.setPosition(col_width * (x + 13), Gdx.graphics.getHeight() - row_height * y);

        stage.addActor(label2);

       /* label2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
              System.out.println("kkkk");
            }
        });*/
    }



    private BitmapFont getFont() {
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
        return font24;
    }

    @Override
    public void show() {
       // Gdx.input.setInputProcessor(stage);


    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
