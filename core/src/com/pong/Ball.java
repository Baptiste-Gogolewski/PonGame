package com.pong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public class Ball
{
    private Body Body;
    private float x, y, Speed, VelX, VelY;
    private int Width, Height;
    private GameScreen GameScreen;
    private Texture Texture;

    public Ball(GameScreen gameScreen)
    {
        // Centre la balle
        this.x = Boot.INSTANCE.getScreenWidth() / 2;
        this.y = Boot.INSTANCE.getScreenHeight() / 2;

        this.Speed = 5;
        this.Texture = new Texture("white.png");
        this.GameScreen = gameScreen;
        this.Width = 32;
        this.Height = 32;
        this.VelX = this.getRandowDirection();
        this.VelY = this.getRandowDirection();
        this.Body = BodyHelper.CreateBody(this.x, this.y, this.Width, this.Height, false, 0, this.GameScreen.getWorld(), ContactType.BALL);
    }

    private float getRandowDirection()
    {
        return ((Math.random() < 0.5) ? 1 : -1);
    }

    public void update()
    {
        // Position du Joueur
        this.x = this.Body.getPosition().x * Const.PPM - (this.Width / 2);
        this.y = this.Body.getPosition().y * Const.PPM - (this.Height / 2);

        this.Body.setLinearVelocity(this.VelX * this.Speed, this.VelY * this.Speed);

        // Score
        if (this.x < 0)
        {
            this.GameScreen.getPlayerAI().Score();
            this.Reset();
        }

        if (this.x > Boot.INSTANCE.getScreenWidth())
        {
            this.GameScreen.getPlayer().Score();
            this.Reset();
        }
    }

    public void render(SpriteBatch spriteBatch)
    {
        spriteBatch.draw(this.Texture, this.x, this.y, this.Width, this.Height);
    }

    public void Reset()
    {
        this.VelX = this.getRandowDirection();
        this.VelY = this.getRandowDirection();
        this.Speed = 5;
        this.Body.setTransform(Boot.INSTANCE.getScreenWidth() / 2 / Const.PPM,Boot.INSTANCE.getScreenHeight() / 2 / Const.PPM, 0);
    }

    public float getY()
    {
        return this.y;
    }

    public void ReverseVelX()
    {
        this.VelX *= -1;
    }

    public void ReverseVelY()
    {
        this.VelY *= -1;
    }

    public void IncSpeed()
    {
        this.Speed *= 1.1f;
    }
}
