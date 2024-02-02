package com.pong;

import org.w3c.dom.Text;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class PlayerPaddle
{
    // Représente le corps physique de notre joueur.
    protected Body Body;
    protected float x, y, Speed, VelY;
    protected int Width, Height, score;
    protected Texture Texture;
    protected GameScreen GameScreen;

    public PlayerPaddle(float x, float y, GameScreen gameScreen)
    {
        this.x = x;
        this.y = y;
        this.GameScreen = gameScreen;
        this.Speed = 6;
        this.Width = 16;
        this.Height = 64;
        this.Texture = new Texture("white.png");
        this.VelY = 0;
        this.score = 0;
    }
}
