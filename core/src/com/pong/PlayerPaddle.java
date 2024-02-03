package com.pong;

import org.w3c.dom.Text;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class PlayerPaddle
{
    // Représente le corps physique de notre joueur.
    protected Body Body;

    protected float x, y, Speed, VelY;
    protected int Width, Height, Score;
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
        this.Body = BodyHelper.CreateBody(this.x, this.y, this.Width, this.Height, false, 10000, this.GameScreen.getWorld(), ContactType.PLAYER);
        this.VelY = 0;
        this.Score = 0;
    }

    // Permet de bouger le body dans notre jeu et de définir x, y
    public void update()
    {
        // Position du Joueur
        this.x = this.Body.getPosition().x * Const.PPM - (this.Width / 2);
        this.y = this.Body.getPosition().y * Const.PPM - (this.Height / 2);
        this.VelY = 0;
    }

    // Draw
    public void render(SpriteBatch spriteBatch)
    {
        spriteBatch.draw(this.Texture, this.x, this.y, this.Width, this.Height);
    }

    public void Score()
    {
        this.Score++;
    }

    public void setScore(int score)
    {
        this.Score = score;
    }

    public int getScore()
    {
        return this.Score;
    }
}
