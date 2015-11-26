package com.kyleschick.hoverboy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kyle on 11/26/2015.
 */
public class InputManager {
    private HashSet<Integer> keysPressed;
    private boolean mousePressed;

    public InputManager() {
        keysPressed = new HashSet();
        mousePressed = false;
    }

    public boolean isKeyPressed(int keycode) {
        if (Gdx.input.isKeyPressed(keycode)) {
            if (!keysPressed.contains(keycode)) {
                keysPressed.add(keycode);
                return true;
            }
        } else  {
            keysPressed.remove(keycode);
        }
        return false;
    }


}
