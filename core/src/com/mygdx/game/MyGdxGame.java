package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;

import java.sql.Time;

public class MyGdxGame extends ApplicationAdapter {
	public SpriteBatch batch;
	public Texture screen;
	public Texture img;
	public ShapeRenderer shapeRenderer;
	public Rectangle rectangle;
	public int plano_x;
	public int plano_y;
	public int width;
	public int height;
	public OrthographicCamera camera;
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
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		img = new Texture("zombie2.png");
		screen = new Texture("street2.jpeg");
		camera = new OrthographicCamera();
		plano_x=300;
		plano_y=300;
		width=80;
		height=100;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);


		batch.draw(screen,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(img,plano_x,plano_y,width,height);
		rectangle = new Rectangle();
		//shapeRenderer.setColor(Color.BLUE);
		//shapeRenderer.rect(plano_x,plano_y,width,height);

		if(!(plano_y < -190)){
			plano_x=plano_x -3;
			plano_y = plano_y - 10;
			width = width + 5;
			height = height + 10;
		}
		if(Gdx.input.isTouched()){
			//Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			Vector2 touch1 = new Vector2(Gdx.input.getX(),Gdx.input.getY());
			//touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			//camera.unproject(touch);
			//Gdx.app.setLogLevel(Application.LOG_DEBUG);

			Circle bullet = new Circle(Gdx.input.getX(),Gdx.input.getY(),2);
			shapeRenderer.circle(touch1.x,touch1.y,30);
			shapeRenderer.setColor(Color.BROWN);

			if(Intersector.overlaps(bullet,rectangle)){
				Gdx.app.setLogLevel(Application.LOG_DEBUG);
				Gdx.app.debug("PLANE", "X plane: " + plano_x + " Y plane: " + plano_y);
				Gdx.app.debug("INPUT", "X input: " + Gdx.input.getX() + " Y plane: " + Gdx.input.getY());
				Gdx.app.debug("INPUT INTERSECTOR", "----------");
				shapeRenderer.setColor(Color.BLACK);
				plano_y = -200;
			}else{
				Gdx.app.setLogLevel(Application.LOG_DEBUG);
				Gdx.app.debug("PLANE", "X plane: " + plano_x + " Y plane: " + plano_y);
				Gdx.app.debug("INPUT", "X input: " + Gdx.input.getX() + " Y plane: " + Gdx.input.getY());
				shapeRenderer.setColor(Color.RED);
			}
		}

		shapeRenderer.end();
		batch.end();

	}



	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		screen.dispose();
	}

}
