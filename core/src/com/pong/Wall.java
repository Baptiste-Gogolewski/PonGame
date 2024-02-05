package com.pong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public class Wall
{
    private Body Body;
    private float x, y;
    private int Width, Height;
    private Texture Texture;

    public Wall(float y, GameScreen gameScreen)
    {
        this.x = Boot.INSTANCE.getScreenWidth() / 2;
        this.y = y;
        this.Width = Boot.INSTANCE.getScreenWidth();
        this.Height = 64;

        this.Texture = new Texture("ColorPaddleWall.png");
        this.Body = BodyHelper.CreateBody(this.x, this.y, this.Width, this.Height, true, 0, gameScreen.getWorld(), ContactType.WALL);
    }

    public void render(SpriteBatch spriteBatch)
    {
        spriteBatch.draw(this.Texture, this.x - (this.Width / 2), this.y - (this.Height / 2), this.Width, this.Height);
    }
}
