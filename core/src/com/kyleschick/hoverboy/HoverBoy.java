package com.kyleschick.hoverboy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HoverBoy extends ApplicationAdapter {
	private static final double GRAVITY = -0.2;
	private static final double JUMP_SPEED = 6;
	public enum State { MENU, INGAME, GAMEOVER, PAUSED }

	SpriteBatch batch;
	Texture backgroundTexture;
	Texture birdTextures[];
	Bird bird;
	Clock clock;
	Boolean spaceDown;
	State state;

	@Override
	public void create () {
		bird = new Bird(80, 300);
		clock = new Clock();
		spaceDown = false;
		batch = new SpriteBatch();
		backgroundTexture = new Texture("background.png");
		birdTextures = new Texture[2];
		birdTextures[0] = new Texture("bird0.png");
		birdTextures[1] = new Texture("bird1.png");
		state = State.MENU;
	}

	@Override
	public void render () {
		clock.tick(60);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		if (state == State.INGAME) {
			batch.draw(backgroundTexture, 0, 0);
			batch.draw(birdTextures[0], bird.getX(), bird.getY());

			if (!spaceDown && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
				bird.setVerticleSpeed(JUMP_SPEED);
				spaceDown = true;
			} else {
				bird.changeVerticleSpeed(GRAVITY);
				spaceDown = Gdx.input.isKeyPressed(Input.Keys.SPACE);
			}
			bird.move();
		} else if (state == State.MENU) {
			if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
				state = State.INGAME;
		}


		batch.end();



	}
}
