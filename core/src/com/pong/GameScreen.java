package com.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen extends ScreenAdapter
{
    private OrthographicCamera OrthographicCamera;
    private SpriteBatch SpriteBatch;

    // Represente le monde physique, notre monde 2D.
    // Elle contient et gère tous les corps physiques, leurs interactions,
    // et simule la physique du monde selon des paramètres définis, comme la gravité.
    private World World;

    // Permet de dessiner les formes des corps physiques de notre monde 2D.
    // visualiser de manière graphique les éléments du monde physique gérés par Box2D
    private Box2DDebugRenderer Box2DDebugRenderer;

    // Game Objects
    private Player Player;
    private Ball Ball;

    public GameScreen(OrthographicCamera camera)
    {
        this.OrthographicCamera = camera;
        // Camera au centre de l'écran
        this.OrthographicCamera.position.set(new Vector3(Boot.INSTANCE.getScreenWidth() / 2, Boot.INSTANCE.getScreenHeight() / 2, 0));
        this.SpriteBatch = new SpriteBatch();
        this.World = new World(new Vector2(0, 0), false);
        this.Box2DDebugRenderer = new Box2DDebugRenderer();

        this.Player = new Player(16, Boot.INSTANCE.getScreenHeight() / 2, this);
        this.Ball = new Ball(this);
    }

    public void update()
    {
        // 1 / 60 = 60 FPS
        World.step(1 / 60f, 6, 2);
        SpriteBatch.setProjectionMatrix(this.OrthographicCamera.combined);

        this.Player.update();
        this.Ball.update();

        // SI la touche ECHAP est pressée
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            // Ferme le jeu
            Gdx.app.exit();
        }

        // Permet de réinitialiser la balle
        if (Gdx.input.isKeyJustPressed(Input.Keys.R))
        {
            this.Ball.Reset();
        }
    }

    @Override
    public void render(float delta)
    {
        update();

        // Efface l'écran avec la couleur spécifiée.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Affiche les textures
        SpriteBatch.begin();

        this.Player.render(this.SpriteBatch);
        this.Ball.render(this.SpriteBatch);

        SpriteBatch.end();
    }

    public World getWorld()
    {
        return this.World;
    }
}
