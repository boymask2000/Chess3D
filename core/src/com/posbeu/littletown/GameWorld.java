package com.posbeu.littletown;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.posbeu.littletown.systems.PathBuilder;
import com.posbeu.littletown.systems.RenderSystem;

import com.posbeu.littletown.systems.VagabondoSystem;
import com.posbeu.littletown.terrain.Terrain;


import lombok.Data;
import lombok.Getter;

@Data
public class GameWorld {
    private static final double SIN = Math.sin(Math.PI / 4);
    private static final float FOV = 67F;
    private ModelBatch modelBatch;
    private Environment environment;

    private Terrain terrain = Pool.getTerrain();

    public Engine getEngine() {
        return engine;
    }

    private Engine engine;

    private PerspectiveCamera perspectiveCamera;

    private ShapeRenderer shapeRenderer;


    // private MyInputTracker inputProcessor;
    private MyInputTrackerPlain inputProcessor;

    public GameWorld() {

        //    ChessEngine chessEngine = new ChessEngine();
        //    Pool.setChessEngine(chessEngine);
        modelBatch = new ModelBatch();
        initEnvironment();
        initModelBatch();
        initPersCamera();
        addSystems();
        addEntities();

        Pool.setGameWorld(this);
        //  initPezzi();
    }


    private void addEntities() {
        createGround();
    }

    private void createGround() {
    /*    for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                EntityFactory.createCell(i, j);*/
    }

    public void remove(Entity entity) {
        engine.removeEntity(entity);

    }

    private void initPersCamera() {


        perspectiveCamera = new PerspectiveCamera(FOV,
                ChessGame.VIRTUAL_WIDTH, ChessGame.VIRTUAL_HEIGHT);
        perspectiveCamera.position.set(25f, 50f, 0);
        //  perspectiveCamera.position.set(500f, 500f, 500f);
        perspectiveCamera.lookAt(0, 0f, 0);
        perspectiveCamera.near = 1f;
        perspectiveCamera.far = 300f;
        perspectiveCamera.update();
        Pool.setCamera(perspectiveCamera);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(perspectiveCamera.combined);

        inputProcessor = new MyInputTrackerPlain();
        //    Gdx.input.setInputProcessor(inputProcessor);
        inputProcessor.setCamera(perspectiveCamera);

        inputProcessor.setShapeRenderer(shapeRenderer);
        terrain.setCamera(perspectiveCamera);


        FitViewport v = new FitViewport(ChessGame.VIRTUAL_WIDTH, ChessGame.VIRTUAL_HEIGHT, perspectiveCamera);
        Pool.setViewport(v);
    }

    private void addSystems() {
        engine = new Engine();
        engine.addSystem(new RenderSystem(modelBatch, environment));

        engine.addSystem(new PathBuilder(modelBatch, environment));
        engine.addSystem(new VagabondoSystem(modelBatch, environment));
        Pool.setEngine(engine);

    }

    public void render(float delta) {

        movement();
        renderWorld(delta);

    }

    private void movement() {
        Vector3 position = perspectiveCamera.position;
        Vector3 rot = new Vector3(20, 0, 20);
        Vector3 tmpV1 = new Vector3(20, 20, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

            perspectiveCamera.translate(0, 0, 2);
            //perspectiveCamera.rotateAround(rot, new Vector3(0, 1, 0), -(float) (Math.PI / 10));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            perspectiveCamera.translate(0, 0, -2);
            //perspectiveCamera.rotateAround(rot, new Vector3(0, 1, 0), (float) (Math.PI / 10));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {

            //       perspectiveCamera.rotateAround(rot, new Vector3(0,0,1),(float) (Math.PI/10));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            perspectiveCamera.translate(-2, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            perspectiveCamera.translate(+2, 0, 0);
        }
        perspectiveCamera.update();
    }


    private void movement0() {

        int delta = 50;
        Vector3 position = perspectiveCamera.position;
        //    perspectiveCamera.transform.getTranslation(position);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            //    position.z+=delta*Gdx.graphics.getDeltaTime();
            position.x -= delta * SIN * Gdx.graphics.getDeltaTime();
            position.z += delta * SIN * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.x -= delta * SIN * Gdx.graphics.getDeltaTime();
            position.z -= delta * SIN * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.x += delta * SIN * Gdx.graphics.getDeltaTime();
            position.z += delta * SIN * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            //   position.z-=delta*Gdx.graphics.getDeltaTime();
            position.x += delta * SIN * Gdx.graphics.getDeltaTime();
            position.z -= delta * SIN * Gdx.graphics.getDeltaTime();
        }
        perspectiveCamera.position.lerp(position, 0.1f);
        perspectiveCamera.update();
    }

    protected void renderWorld(float delta) {
        modelBatch.begin(perspectiveCamera);
        engine.update(delta);
        modelBatch.end();
    }

    public void dispose() {


        modelBatch.dispose();
        modelBatch = null;
    }

    private void initEnvironment() {


        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

    }

    private void initModelBatch() {
        modelBatch = new ModelBatch();
    }
    /*The ModelBatch is one of the objects, which require disposing, hence we
    add it to the dispose function. */

    /*With the camera set we can now fill in the resize function as well*/
    public void resize(int width, int height) {
        perspectiveCamera.viewportHeight = height;
        perspectiveCamera.viewportWidth = width;
    }
    //and set up the render function with the modelbatch
    public MyInputTrackerPlain getInputProcessor() {
        return inputProcessor;
    }

    public void setInputProcessor(MyInputTrackerPlain inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

}
