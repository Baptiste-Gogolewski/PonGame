package com.pong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Boot extends Game
{
    public static Boot INSTANCE;
    private int ScreenWidth, ScreenHeight;
    private OrthographicCamera OrthographicCamera;

    public Boot()
    {
        INSTANCE = this;
    }

    public void setScreenWidth(int screenWidth)
    {
        this.ScreenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight)
    {
        this.ScreenHeight = screenHeight;
    }

    public int getScreenWidth()
    {
        return ScreenWidth;
    }

    public int getScreenHeight()
    {
        return ScreenHeight;
    }

    @Override
    public void create()
    {
        this.ScreenWidth = Gdx.graphics.getWidth();
        this.ScreenHeight = Gdx.graphics.getHeight();
        this.OrthographicCamera = new OrthographicCamera();

        /*
        * Le premier argument false spécifie que l'axe Y ne doit pas être inversé.
        * Si vous définissez cet argument sur true, cela signifierait que l'origine de la caméra est en haut à gauche de l'écran (avec l'axe Y croissant vers le bas),
        * ce qui est courant dans certains systèmes de coordonnées.
        * En définissant cet argument sur false, l'origine de la caméra est en bas à gauche (avec l'axe Y croissant vers le haut),
        * ce qui est plus courant dans la plupart des jeux.
        */
        this.OrthographicCamera.setToOrtho(false, this.ScreenWidth, this.ScreenHeight);

        setScreen(new GameScreen(this.OrthographicCamera));
    }
}
