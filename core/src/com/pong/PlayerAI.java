package com.pong;

public class PlayerAI extends PlayerPaddle
{
    private int Offset = 10;

    public PlayerAI(float x, float y, com.pong.GameScreen gameScreen)
    {
        super(x, y, gameScreen);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void update()
    {
        super.update();

        // IA magic
        Ball Ball = this.GameScreen.getBall();

        if (Ball.getY() + Offset > this.y && Ball.getY() - Offset > this.y)
        {
            this.VelY = 1;
        }

        if (Ball.getY() + Offset < this.y && Ball.getY() - Offset < this.y)
        {
            this.VelY = -1;
        }

        // Permet de bouger
        this.Body.setLinearVelocity(0, this.VelY * this.Speed);
    }

}
