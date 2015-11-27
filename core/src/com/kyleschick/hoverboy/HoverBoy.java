package com.kyleschick.hoverboy;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import org.w3c.dom.css.Rect;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class HoverBoy extends ApplicationAdapter {
	private static final float GRAVITY = -18;
	private static final float JUMP_SPEED = 400;
	private static final float OBSTACLE_INTERVAL = 1500;
	private static final float HORIZONTAL_SPEED = 175;

	public enum State { MENU, INGAME, GAMEOVER, PAUSED }

	SpriteBatch batch;
	InputManager inputManager;
	long obstacleTime;
	Texture backgroundTexture;
	Texture birdTextures[];
	Texture pipeTextures[];
	Rectangle bird;
	State state;
	ArrayList<Rectangle> obstaclesTop;
	ArrayList<Rectangle> obstaclesBottom;
	float birdSpeed;

	@Override
	public void create () {
		bird = new Rectangle(70, 300, 40, 30);
		obstacleTime = 0;
		batch = new SpriteBatch();
		obstaclesTop = new ArrayList();
		obstaclesBottom = new ArrayList();
		inputManager = new InputManager();
		backgroundTexture = new Texture("background.png");
		birdTextures = new Texture[2];
		birdTextures[0] = new Texture("bird0.png");
		birdTextures[1] = new Texture("bird1.png");
		pipeTextures = new Texture[2];
		pipeTextures[0] = new Texture("topPipe.png");
		pipeTextures[1] = new Texture("bottomPipe.png");
		state = State.MENU;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		if (state == State.INGAME) {
			batch.draw(backgroundTexture, 0, 0);

			if (inputManager.isKeyPressed(Input.Keys.SPACE))
				birdSpeed = JUMP_SPEED;
			else
				birdSpeed += GRAVITY;

			move(bird, 0, birdSpeed);
			if (birdSpeed < 0)
				batch.draw(birdTextures[0], bird.getX(), bird.getY());
			else
				batch.draw(birdTextures[1], bird.getX(), bird.getY());

			if (inputManager.isKeyPressed(Input.Keys.ENTER))
				state = State.PAUSED;

			if (System.currentTimeMillis() - obstacleTime >= OBSTACLE_INTERVAL) {
				obstacleTime = System.currentTimeMillis();
				spawnObstacles();
			}

			for (int i = 0; i < obstaclesTop.size(); ++i) {
				move(obstaclesTop.get(i), -HORIZONTAL_SPEED, 0);
				move(obstaclesBottom.get(i), -HORIZONTAL_SPEED, 0);
				batch.draw(pipeTextures[0], obstaclesTop.get(i).x, obstaclesTop.get(i).y);
				batch.draw(pipeTextures[1], obstaclesBottom.get(i).x, obstaclesBottom.get(i).y);
			}

		} else if (state == State.MENU) {
			if (inputManager.isKeyPressed(Input.Keys.ENTER)) {
				state = State.INGAME;
				obstacleTime = System.currentTimeMillis();
			}
		} else if (state == State.PAUSED) {
			if (inputManager.isKeyPressed(Input.Keys.ENTER))
				state = State.INGAME;
		}

		batch.end();
	}

	public void move(Rectangle obj, float xVelocity, float yVelocity) {
		obj.x += xVelocity * Gdx.graphics.getDeltaTime();
		obj.y += yVelocity * Gdx.graphics.getDeltaTime();
	}

	public void spawnObstacles() {
		int openingHeight = MathUtils.random(100, 450) ;
		obstaclesTop.add(new Rectangle(600, 640 - openingHeight, 100, 500));
		obstaclesBottom.add(new Rectangle(600, -openingHeight, 100, 500));
	}
}
