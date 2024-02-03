package com.pong;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class GameContactListener implements ContactListener
{
    private GameScreen GameScreen;

    public GameContactListener(GameScreen gameScreen)
    {
        this.GameScreen = gameScreen;
    }

    // A chaque fois que les body des box2D rentrent en collision ils créent un beginContact event
    @Override
    public void beginContact(Contact contact) {
        Fixture A = contact.getFixtureA();
        Fixture B = contact.getFixtureB();

        if (A == null || B == null)
            return;
        
        if (A.getUserData() == null || B.getUserData() == null)
            return;
        
        if (A.getUserData() == ContactType.BALL || B.getUserData() == ContactType.BALL)
        {
            // Collision Ball - Player
            if (A.getUserData() == ContactType.PLAYER || B.getUserData() == ContactType.PLAYER)
            {
                this.GameScreen.getBall().ReverseVelX();
                this.GameScreen.getBall().IncSpeed();
            }

            // Collision Ball - Wall
            if (A.getUserData() == ContactType.WALL || B.getUserData() == ContactType.WALL)
            {
                this.GameScreen.getBall().ReverseVelY();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        
    }

}
