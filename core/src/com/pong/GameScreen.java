package com.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    private GameContactListener GameContactListener;
    private TextureRegion Numbers[];

    // Color
    private Color BackgroundColor;

    // Game Objects
    private Player Player;
    private Ball Ball;
    private Wall WallTop, WallBottom;
    private PlayerAI PlayerAI;

    public GameScreen(OrthographicCamera camera)
    {
        this.OrthographicCamera = camera;
        // Camera au centre de l'écran
        this.OrthographicCamera.position.set(new Vector3(Boot.INSTANCE.getScreenWidth() / 2, Boot.INSTANCE.getScreenHeight() / 2, 0));
        this.SpriteBatch = new SpriteBatch();
        this.World = new World(new Vector2(0, 0), false);
        this.Box2DDebugRenderer = new Box2DDebugRenderer();

        this.GameContactListener = new GameContactListener(this);
        this.World.setContactListener(this.GameContactListener);

        this.Numbers = LoadTextureSpriteNumbers("numbers.png", 10);

        // Color
        this.BackgroundColor = ToColor(41, 128, 185, 1);

        this.Player = new Player(32, Boot.INSTANCE.getScreenHeight() / 2, this);
        this.Ball = new Ball(this);
        this.WallTop = new Wall(32, this);
        this.WallBottom = new Wall(Boot.INSTANCE.getScreenHeight() - 32, this);
        this.PlayerAI = new PlayerAI(Boot.INSTANCE.getScreenWidth() - 32, Boot.INSTANCE.getScreenHeight() / 2, this);
    }

    public void update()
    {
        // 1 / 60 = 60 FPS
        World.step(1 / 60f, 6, 2);

        this.OrthographicCamera.update();
        this.Player.update();
        this.Ball.update();
        this.PlayerAI.update();;

        SpriteBatch.setProjectionMatrix(this.OrthographicCamera.combined);

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
            this.Player.setScore(0);
            this.PlayerAI.setScore(0);
        }
    }

    @Override
    public void render(float delta)
    {
        update();

        // Efface l'écran avec la couleur spécifiée.
        Gdx.gl.glClearColor(this.BackgroundColor.r, this.BackgroundColor.g, this.BackgroundColor.b, this.BackgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Affiche les textures
        SpriteBatch.begin();

        this.Player.render(this.SpriteBatch);
        this.Ball.render(this.SpriteBatch);
        this.WallTop.render(SpriteBatch);
        this.WallBottom.render(SpriteBatch);
        this.PlayerAI.render(SpriteBatch);

        this.DrawNumbers(SpriteBatch, this.Player.getScore(), 64, Boot.INSTANCE.getScreenHeight() - 55, 30, 42);
        this.DrawNumbers(SpriteBatch, this.PlayerAI.getScore(), Boot.INSTANCE.getScreenWidth() - 96, Boot.INSTANCE.getScreenHeight() - 55, 30, 42);

        SpriteBatch.end();

        // Permet de voir les boxs de nos objets
        // Pour le debug
        //this.Box2DDebugRenderer.render(this.World, this.OrthographicCamera.combined.scl(Const.PPM));
    }

    // TextureRegion.split() retourne une liste en 2 dimension. Notre Texture a une dimension donc on rajoute [0]
    private TextureRegion[] LoadTextureSpriteNumbers(String filename, int colums)
    {
        Texture Texture = new Texture(filename);
        return TextureRegion.split(Texture, Texture.getWidth() / colums, Texture.getHeight())[0];
    }

    private void DrawNumbers(SpriteBatch spriteBatch, int number, float x, float y, float width, float height)
    {
        if (number < 10)
        {
            spriteBatch.draw(this.Numbers[number], x, y, width, height);
        }
        else
        {
            spriteBatch.draw(this.Numbers[Integer.parseInt(("" + number).substring(0, 1))], x, y, width, height);
            spriteBatch.draw(this.Numbers[Integer.parseInt(("" + number).substring(1, 2))], x + 20, y, width, height);
        }
    }

    // Cette fonction permet de convertir 
    // private Color hexToColor(String hex) {
    //     Color color = new Color();

    //     // Supprimer le préfixe "#" si présent
    //     if (hex.startsWith("#")) {
    //         hex = hex.substring(1);
    //     }

    //     // Convertir la valeur hexadécimale en entier
    //     int intValue = Integer.parseInt(hex, 16);

    //     // Extraire les composantes de couleur
    //     int red = (intValue >> 16) & 0xFF;
    //     int green = (intValue >> 8) & 0xFF;
    //     int blue = intValue & 0xFF;

    //     // Définir la couleur avec les composantes RGB
    //     color.set(red / 255f, green / 255f, blue / 255f, 1f); // Alpha à 1 par défaut
    //     return color;
    // }

    private Color ToColor(int r, int g, int b, int a)
    {
        return new Color(r / 255f, g / 255f, b / 255f, a);
    }

    public World getWorld()
    {
        return this.World;
    }

    public Ball getBall()
    {
        return this.Ball;
    }

    public Player getPlayer()
    {
        return this.Player;
    }

    public PlayerAI getPlayerAI()
    {
        return this.PlayerAI;
    }
}
