package com.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player extends PlayerPaddle
{
    public Player(float x, float y, GameScreen gameScreen)
    {
        super(x, y, gameScreen);
    }

    public void update()
    {
        super.update();

        if (Gdx.input.isKeyPressed(Input.Keys.W))
            this.VelY = 1;
        
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            this.VelY = -1;
        
        
        // Définis la vélocité de notre body pour faire bouger le Paddle
        this.Body.setLinearVelocity(0, this.VelY * this.Speed);

    }


}
