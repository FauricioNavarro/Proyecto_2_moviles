package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;

import java.sql.Time;

public class MyGdxGame extends ApplicationAdapter {
	public final SpriteBatch batch = new SpriteBatch();
	public static Texture screen;
	public static Texture img;
	int plano_x=300;
	int plano_y=300;
	int width=80;
	int height=100;
	/*
	Timer.Task mytask = new Timer.Task(){
		@Override
		public void run() {
			batch.draw(screen,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			batch.draw(img,plano_x,plano_y,width,height);
			plano_y = plano_y - 50;
			width = width + 60;
			height = height + 60;
		}
	};
	*/
	@Override
	public void create () {
		//batch = new SpriteBatch();
		img = new Texture("zombie2.png");
		screen = new Texture("street2.jpeg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(screen,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		/*
		for(int i = 0;i<10;i++){
			batch.draw(screen,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			batch.draw(img,plano_x,plano_y,width,height);
			plano_y = plano_y - 50;
			width = width + 60;
			height = height + 60;
		}*/
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				batch.draw(img,plano_x,plano_y,width,height);
				plano_y = plano_y - 50;
				width = width + 60;
				height = height + 60;
			}
		},1f,2f);

		//Timer.schedule(mytask,5f,4f);
		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		screen.dispose();
	}
}
