package com.pong;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class BodyHelper
{
    public static Body CreateBody(float x, float y, float width, float height, boolean isStatic, float density, World world, ContactType contactType)
    {
        BodyDef BodyDef = new BodyDef();
        BodyDef.type = ((isStatic == false) ? com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody : com.badlogic.gdx.physics.box2d.BodyDef.BodyType.StaticBody);
        BodyDef.position.set(x / Const.PPM, y / Const.PPM);
        BodyDef.fixedRotation = true;   // Evite que notre body tourne sur lui meme
        Body Body = world.createBody(BodyDef);

        PolygonShape PolygonShape = new PolygonShape();
        // Centre la forme sur le corps physique
        PolygonShape.setAsBox(width / 2 / Const.PPM, height / 2 / Const.PPM);

        // Propriete physique de l'objet
        /*
         * le concept de fixtures pour attacher des formes géométriques aux corps physiques (Body)
         * afin de définir leur forme physique, leur densité, leur frottement, et leur restitution (rebond).
        */
        FixtureDef FixtureDef = new FixtureDef();
        FixtureDef.shape = PolygonShape;
        FixtureDef.density = density;
        Body.createFixture(FixtureDef).setUserData(contactType);

        PolygonShape.dispose();
        return Body;
    }
}
